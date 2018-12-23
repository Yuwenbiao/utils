package com.example.util.操作word文档;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * word文档解析
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/16 21:27
 */
public class ParseWord {
    public static void main(String[] args) throws IOException {
//        InputStream is = new FileInputStream("test.doc");
//        HWPFDocument doc = new HWPFDocument(is);
//        Range range = doc.getRange();
//        range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//        //输出文本
//        System.out.println(doc.getDocumentText());
//        OutputStream os = new FileOutputStream("output.doc");
//        doc.write(os);
//        is.close();
//        os.close();
        createFile();
    }

    private static void createFile() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String outPutFileName = "流量宝平台施工方案" + simpleDateFormat.format(new Date()) + ".doc";
        try (InputStream inputStream = new FileInputStream("流量宝平台施工方案模板.doc");
             OutputStream outputStream = new FileOutputStream(outPutFileName) {
             }) {
            HWPFDocument doc = new HWPFDocument(inputStream);
            Range range = doc.getRange();
            range.replaceText("${reportDate}", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
            doc.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
