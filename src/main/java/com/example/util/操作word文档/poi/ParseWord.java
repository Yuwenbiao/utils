package com.example.util.操作word文档.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * word文档解析
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/16 21:27
 */
public class ParseWord {
    public static void main(String[] args) {
//        createFile();
//        readDoc();
//        operateDoc();
//        addTableDoc();
        repalce();
    }

    private static Optional<HWPFDocument> getHWPFDocument(String inputDoc) {
        try (var is = new FileInputStream(inputDoc)) {
            return Optional.of(new HWPFDocument(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static Optional<XWPFDocument> getXWPFDocument(String inputDoc) {
        try (var is = new FileInputStream(inputDoc)) {
            return Optional.of(new XWPFDocument(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * 读取word文档内容
     */
    private static void readDoc() {
        getHWPFDocument("test.doc").ifPresent(readDocConsumer);
    }

    private static Consumer<HWPFDocument> readDocConsumer = hwpfDocument -> {
        readTable(hwpfDocument.getRange());
        //为什么无法读取非表格内容？
        System.out.println(hwpfDocument.getDocumentText());
    };

    private static void readTable(Range range) {
        var tableIter = new TableIterator(range);
        while (tableIter.hasNext()) {
            var table = tableIter.next();
            var rowNum = table.numRows();
            for (var i = 0; i < rowNum; i++) {
                var row = table.getRow(i);
                var cellNum = row.numCells();
                for (var j = 0; j < cellNum; j++) {
                    var cell = row.getCell(j);
                    System.out.println(cell.text().trim());
                }
            }
        }
    }


    /**
     * 操作word文档内容，替换标识
     */
    private static void operateDoc() {
        getHWPFDocument("test.doc").ifPresent(operateDocConsumer);
    }

    private static Consumer<HWPFDocument> operateDocConsumer = doc -> {
        doc.getRange().replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        try (var os = new FileOutputStream("output.doc")) {
            doc.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    /**
     * 增加表格
     */
    private static void addTableDoc() {
        getXWPFDocument("test.docx").ifPresent(ParseWord::addTable);
    }

    private static void addTable(XWPFDocument document) {
        try (OutputStream os = new FileOutputStream("output.docx")) {
            // POIFSFileSystem pfs = new POIFSFileSystem(fileInputStream);
            //获取所有表格
            var tables = document.getTables();
            //这里简单取第一个表格
            var table = tables.get(0);
            //获取表头
            var header = table.getRow(0);
            copy(table, header, 1);
            //获取到刚刚插入的行
            var row = table.getRow(0);
            //设置单元格内容
            row.getCell(0).setText("1");
            row.getCell(1).setText("test");

            row = table.getRow(1);
            row.getCell(0).setText("2");
            row.getCell(1).setText("copy");
            //写到目标文件
            document.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy(XWPFTable table, XWPFTableRow sourceRow, int rowIndex) {
        //在表格指定位置新增一行
        var targetRow = table.insertNewTableRow(rowIndex);
        //复制行属性
        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
        var cellList = sourceRow.getTableCells();
        if (null == cellList) {
            return;
        }
        //复制列及其属性和内容
        XWPFTableCell targetCell;
        for (var sourceCell : cellList) {
            targetCell = targetRow.addNewTableCell();
            //列属性
            targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
            //段落属性
            if (sourceCell.getParagraphs() != null && sourceCell.getParagraphs().size() > 0) {
                targetCell.getParagraphs().get(0).getCTP().setPPr(sourceCell.getParagraphs().get(0).getCTP().getPPr());
                if (sourceCell.getParagraphs().get(0).getRuns() != null && sourceCell.getParagraphs().get(0).getRuns().size() > 0) {
                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                    cellR.setText(sourceCell.getText());
                    cellR.setBold(sourceCell.getParagraphs().get(0).getRuns().get(0).isBold());
                } else {
                    targetCell.setText(sourceCell.getText());
                }
            } else {
                targetCell.setText(sourceCell.getText());
            }
        }
    }

    /**
     * 根据word模板创建新的文档
     */
    private static void createFile() {
        var docNum = new SimpleDateFormat("yyyyMMdd");
        var startTime = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        var affectionTime = new SimpleDateFormat("hh:mm");

        var now = new Date();
        var outPutFileName = "xxx平台施工方案" + docNum.format(new Date()) + ".docx";
        try (InputStream inputStream = new FileInputStream("xxx平台施工方案模板.doc"); OutputStream outputStream = new FileOutputStream(outPutFileName)) {
            var doc = new HWPFDocument(inputStream);
            var range = doc.getRange();
            range.replaceText("${reportDate}", new SimpleDateFormat("yyyy年MM月dd日").format(now));
            range.replaceText("${reportNumber}", docNum.format(now));
            range.replaceText("${startExecutionTime}", startTime.format(now));
            range.replaceText("${stopExecutionTime}", startTime.format(now));
            range.replaceText("${affectionStartTime}", affectionTime.format(now));
            range.replaceText("${affectionStopTime}", affectionTime.format(now));
            range.replaceText("${project}", getProject());
            range.replaceText("${developer}", "余文彪");
            range.replaceText("${developerPhone}", "19927672430");
            doc.write(outputStream);

            getXWPFDocument("xxx平台施工方案20181227.docx").ifPresent(ParseWord::addTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProject() {
        var projectName = "1.xxx运营管理平台\n";
        var projectRout = "xxxxx";
        return projectName + projectRout;
    }

    private static void repalce() {
        var doc = getXWPFDocument("llb.docx").get();
        var list = doc.getParagraphs();
        for (XWPFParagraph paragraph : list) {
            var runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
                String text = run.getText(0);
                text = text.replace("${test}", "425452");
                run.setText(text);
            }
        }
//        for (int i = 0; i < list.size(); i++) {
//            var runs = list.get(i).getRuns();
//            for (int j = runs.size()-1; j >=0; j--) {
//                list.get(i).removeRun(j);
//            }
//
//            var paragraphRun = list.get(i).createRun();
//            paragraphRun.setText("jsjfk");
//            System.out.println(list.get(i).getParagraphText());
//        }
        try (OutputStream os = new FileOutputStream("outpuffdst.docx")) {
            doc.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
