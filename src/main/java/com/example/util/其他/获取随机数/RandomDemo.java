package com.example.util.其他.获取随机数;

import java.util.Random;

/**
 * 获取随机数
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/2/1 14:13
 */
public class RandomDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getRandom());
        }
    }

    /**
     * 获取10000至10100之间的随机数
     */
    private static int getRandom() {
        Random randomRank = new Random();
        return randomRank.nextInt(100) + 10000;
    }
}
