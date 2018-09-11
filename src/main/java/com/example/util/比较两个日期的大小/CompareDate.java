package com.example.util.比较两个日期的大小;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 比较两个日期的大小
 */
public class CompareDate {
    /**
     * 把字符串类型的日期数据转化成长整型，然后比较大小。
     */
    private static boolean compareByInteger(String beginTime, String endTime) {
        //如果日期格式有时分秒，则”-“写成”[-\s:]”
        return Long.valueOf(endTime.replaceAll("[-\\s:]", "")) > Long.valueOf(beginTime.replaceAll("[-\\s:]", ""));
    }

    /**
     * 直接进行字符串比较
     */
    private static boolean compareByString(String beginTime, String endTime) {
        //注意：如果一个日期格式是2016-01-01，但是另一个日期格式是2016-1-1时，直接使用字符串进行比较就会存在问题。
        return beginTime.compareTo(endTime) < 0;
    }

    /**
     * 用SimpleDateFormat转化成日期型再判断
     */
    private static boolean compareBySimpleDateFormat(String beginTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bt = sdf.parse(beginTime);
        Date et = sdf.parse(endTime);

        return bt.before(et);
    }

    public static void main(String[] args) {
        String beginTime = "2014-08-15 10:22:22";
        String endTime = "2014-09-02 11:22:22";

        System.out.println(compareByInteger(beginTime, endTime));
    }
}
