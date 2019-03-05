package com.example.util.文件操作.普通文件操作;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

/**
 * 读写普通格式文件
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-5 下午3:32
 */
public class ParseTxt {
    private static List<String> content = Arrays.asList("1", "2", "3", "4");

    public static void main(String[] args) {
        writeFile();
    }

    public void readFile() {

    }

    /**
     * 将字符串集合写入到txt文件中
     */
    public static void writeFile() {
        File f = new File("content.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (String str : content) {
                bw.write(str);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
