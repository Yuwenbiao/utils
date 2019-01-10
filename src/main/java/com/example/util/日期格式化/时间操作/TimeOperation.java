package com.example.util.日期格式化.时间操作;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 时间操作
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/1/10 14:52
 */
public class TimeOperation {
    public static void main(String[] args) {
        System.out.println(getLastDay());
    }

    /**
     * 获取昨天的日期并格式化
     */
    private static String getLastDay() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
    }
}
