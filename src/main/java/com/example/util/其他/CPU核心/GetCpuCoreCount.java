package com.example.util.其他.CPU核心;

/**
 * 获取CPU核心数
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-28 上午11:19
 */
public class GetCpuCoreCount {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
