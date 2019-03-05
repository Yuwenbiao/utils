package com.example.util.日志.slf4j注解的使用;

import lombok.extern.slf4j.Slf4j;

/**
 * slf4f注解的使用
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-5 下午3:55
 */
@Slf4j
public class Slf4jDemo {
    public static void main(String[] args) {
        slf4j();
    }

    private static void slf4j() {
        log.info("slf4j注解的使用");
    }
}
