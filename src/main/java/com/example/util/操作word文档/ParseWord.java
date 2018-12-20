package com.example.util.操作word文档;

import org.apache.poi.hwpf.HWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * word文档解析
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/16 21:27
 */
public class ParseWord {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("test.doc");
        HWPFDocument doc = new HWPFDocument(is);
        //输出文本
        System.out.println(doc.getDocumentText());
    }
}
