package com.example.util.集合操作;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List操作
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/1/31 10:50
 */
public class ListDemo {
    public static void main(String[] args) {
//        subList();
//        filterList();
        sortDemo();
    }


    /**
     * 初始化List
     */
    private static List<String> getList() {
        return Arrays.asList("a", "b", "c");
    }

    /**
     * 根据索引截取集合
     */
    private static void subList() {
        List<String> list = getList();
        System.out.println(list.subList(0, 2));
    }

    /**
     * 过滤集合
     */
    private static void filterList() {
        List<String> list = getList();
        System.out.println(list.stream().filter(s -> !"a".equals(s)).collect(Collectors.toList()));
    }

    /**
     * 排序
     */
    private static void sortDemo() {
        List<TestDemo> testDemos = Arrays.asList(new TestDemo("b"), new TestDemo("a"));
        testDemos.sort(Comparator.comparing(a -> a.time));
        for (TestDemo testDemo : testDemos) {
            System.out.println(testDemo.time);
        }
    }

    static class TestDemo {
        String time;

        public TestDemo(String time) {
            this.time = time;
        }
    }
}
