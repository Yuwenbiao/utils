package com.example.util.文件操作.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(filePath);
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            //对表格进行遍历
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
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
    public static void writeExcel(List<Map> dataList, String outFilePath) {
        try (FileOutputStream outputStream = new FileOutputStream(outFilePath)) {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            Sheet sheet = xssfWorkbook.createSheet();

            //将数据循环写入每一行
            for (int i = 0; i < dataList.size(); i++) {
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
