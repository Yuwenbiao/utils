package com.example.util.文件操作.解析HTML文件;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 解析HTML文件
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-5 下午4:47
 */
public class ParseHTML {
    private static void parse() throws IOException {
        File file = new File("/home/ywb/Downloads/226104.html");
        Document doc = Jsoup.parse(file, "utf-8");
        Elements elements = doc.select("input[name=doctor_id]");
        String id = elements.get(0).val();
        System.out.println(id);
    }
}
