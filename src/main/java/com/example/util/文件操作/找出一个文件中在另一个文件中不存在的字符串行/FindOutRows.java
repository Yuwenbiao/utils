package com.example.util.文件操作.找出一个文件中在另一个文件中不存在的字符串行;

import com.example.util.文件操作.普通文件操作.ParseTxt;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 找出一个文件中在另一个文件中不存在的字符串行
 *
 * @author yuwb@corp.21cn.com
 * @date 19-7-11 上午10:58
 */
public class FindOutRows {
    private static List<String> rowsNotExist = new ArrayList<>();
    private static List<String> rows2 = new ArrayList<>();

    public static void main(String[] args) {
        findOut();
        ParseTxt.writeFile(rowsNotExist, "hospital.txt");
    }

    private static void findOut() {
        try (Stream<String> lines1 = Files.lines(Paths.get("/home/ywb/Desktop/stand.csv"), Charset.defaultCharset());
             Stream<String> lines2 = Files.lines(Paths.get("/home/ywb/Desktop/level.csv"), Charset.defaultCharset())) {
            rows2 = lines2.collect(toList());
            rowsNotExist = lines1.filter(row1 -> rows2.stream().noneMatch(row2 -> row2.equals(row1))).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
