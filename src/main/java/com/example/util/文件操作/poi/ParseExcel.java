package com.example.util.文件操作.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel文件
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-5 下午3:27
 */
public class ParseExcel {
    /**
     * 读取xlsx格式的Excel文件
     *
     * @param filePath 文件路径
     */
    public void readExcel(String filePath) {
        XSSFWorkbook xssfWorkbook;
        try {
            xssfWorkbook = new XSSFWorkbook(filePath);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据集合写入到Excel中
     *
     * @param dataList 数据集合
     */
    public static void writeExcel(List<Map> dataList) {
        XSSFWorkbook xssfWorkbook;
        try (FileOutputStream outputStream = new FileOutputStream("/home/ywb/Documents/test/testO.xlsx")) {
            xssfWorkbook = new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet();
            int lastRowNum = dataList.size();
            for (int i = 0; i < lastRowNum; i++) {
                Row row = sheet.createRow(i);
                Map dataMap = dataList.get(i);

                int cellIndex = 0;
                for (Object value : dataMap.values()) {
                    row.createCell(cellIndex++).setCellValue(String.valueOf(value));
                }
            }
            xssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
