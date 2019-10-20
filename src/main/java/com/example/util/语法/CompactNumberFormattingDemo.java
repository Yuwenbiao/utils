package com.example.util.语法;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 数字压缩格式化
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/10/20 18:01
 */
public class CompactNumberFormattingDemo {
    public static void main(String[] args) {
        var cnf = NumberFormat.getCompactNumberInstance(Locale.CHINA, NumberFormat.Style.SHORT);
        System.out.println(cnf.format(1_0000));
        System.out.println(cnf.format(1_9200));
        System.out.println(cnf.format(1_000_000));
        System.out.println(cnf.format(1L << 30));
        System.out.println(cnf.format(1L << 40));
        System.out.println(cnf.format(1L << 50));

        var cnf2 = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println(cnf2.format(1_0000));
        System.out.println(cnf2.format(1_9200));
        System.out.println(cnf2.format(1_000_000));
        System.out.println(cnf2.format(1L << 30));
        System.out.println(cnf2.format(1L << 40));
        System.out.println(cnf2.format(1L << 50));
    }
}
