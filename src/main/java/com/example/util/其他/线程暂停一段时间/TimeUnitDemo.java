package com.example.util.其他.线程暂停一段时间;

import java.util.concurrent.TimeUnit;

/**
 * 线程暂停一段时间
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/1/31 14:54
 */
public class TimeUnitDemo {
    public static void main(String[] args) {
        stopSometime();
    }

    private static void stopSometime(){
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("暂停");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
