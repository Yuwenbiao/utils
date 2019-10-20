package com.example.util.语法;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.FRIDAY;

/**
 * Switch新特性
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/10/20 8:53
 */
public class SwitchDemo {
    private static DayOfWeek day = FRIDAY;

    public static void main(String[] args) {
        demo1();
    }

    /**
     * Java 12用法
     */
    static void demo1() {
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY:
                System.out.println(6);
            case TUESDAY:
                System.out.println(7);
            case THURSDAY, SATURDAY:
                System.out.println(8);
            case WEDNESDAY:
                System.out.println(9);
        }

        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
            case TUESDAY -> System.out.println(7);
            case THURSDAY, SATURDAY -> System.out.println(8);
            case WEDNESDAY -> System.out.println(9);
        }

        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
        System.out.println(numLetters);
    }

    /**
     * java 13用法
     */
    static void demo2() {
        int date = switch (day) {
            //需要注意的是，在使用yield时，必须要有default条件?
            case MONDAY, FRIDAY, SUNDAY:
                yield 6;
            case TUESDAY:
                yield 7;
            case THURSDAY, SATURDAY:
                yield 8;
            case WEDNESDAY:
                yield 9;
        };
        System.out.println(date);
    }
}
