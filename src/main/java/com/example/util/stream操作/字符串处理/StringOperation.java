package com.example.util.stream操作.字符串处理;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * 字符串处理
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/1/10 9:36
 */
public class StringOperation {
    public static void main(String[] args) {
        System.out.println(parseString("a=b&c=d"));
    }

    /**
     * 解析a=b&c=d格式的字符串并包装成Map
     */
    private static Map<String, String> parseString(String str) {
        return Arrays.stream(str.split("&")).map(para -> para.split("=")).collect(toMap(para -> para[0], para -> para[1]));
    }
}
