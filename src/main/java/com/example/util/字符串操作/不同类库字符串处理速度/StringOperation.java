package com.example.util.字符串操作.不同类库字符串处理速度;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 字符串处理测试
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-1 下午4:57
 */
public class StringOperation {
    public static void main(String[] args) {
        javaApi();
    }

    /**
     * 使用Java自带类库
     */
    private static void javaApi() {
        try (Stream<String> lines = Files.lines(Paths.get("测试字符串"), Charset.defaultCharset())) {
            long start = System.nanoTime();
//            lines.forEach(str -> StringUtils.replaceChars(str, "'测试'", "测试完毕"));
//            lines.forEach(str -> str.replace("测试", "测试完毕"));
            long invocationTime = ((System.nanoTime() - start) / 1_000_000);
            System.out.println("测试完毕，耗时： " + invocationTime + "毫秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
