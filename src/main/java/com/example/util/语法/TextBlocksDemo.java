package com.example.util.语法;

/**
 * 文本块
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/10/20 9:48
 */
public class TextBlocksDemo {
    public static void main(String[] args) {
        String html2 = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;

        System.out.println(html2);
    }
}
