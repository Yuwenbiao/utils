package com.example.util.文件操作.poi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

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
}
