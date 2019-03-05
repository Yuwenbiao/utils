package com.example.util.字符串操作.不同类库字符串处理速度;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 字符串处理测试
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-1 下午4:57
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Threads(Threads.MAX)
@Warmup(iterations = 1, time = 3)
@Measurement(iterations = 3, time = 3)
public class StringOperation {
    private static String testString = "这是一段字符串，这是一段字符串，这是一段字符串，这是一段字符串，这是一段字符串，这是一段字符串，" +
            "这是一段字符串，这是一段字符串，这是一段字符串，这是一段字符串，这是一段字符串，测试，这是一段字符串，这是一段字符串，这是一段字符串";

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(StringOperation.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    /**
     * 字符串替换（String）
     */
    @Benchmark
    public String replace() {
        return testString.replace("测试", "测试完毕");
    }

    /**
     * 字符串替换（Apache）
     */
    @Benchmark
    public String replaceByApache() {
        return StringUtils.replace(testString, "测试", "测试完毕");
    }

    /**
     * 字符串替换（Guava）
     * 备注：该方法匹配的是字符。
     */
    @Benchmark
    public String replaceByGuava() {
        return CharMatcher.is('测').replaceFrom(testString, "测试完毕");
    }

    /**
     * 字符串拼接（String）
     */
    @Benchmark
    public String concat() {
        return testString.concat("拼接后的字符串");
    }

    /**
     * 字符串拼接（Apache）
     */
    @Benchmark
    public String concatByApache() {
        return StringUtils.join(testString, "拼接后的字符串");
    }

    /**
     * 字符串拼接（Guava）
     */
    @Benchmark
    public String concatByGuava() {
        return Joiner.on("").join(testString, "拼接后的字符串");
    }

    /**
     * 字符串分割（String）
     */
    @Benchmark
    public String[] split() {
        return testString.split("，");
    }

    /**
     * 字符串分割（Apache）
     */
    @Benchmark
    public String[] splitByApache() {
        return StringUtils.split(testString, "，");
    }

    /**
     * 字符串分割（Guava）
     */
    @Benchmark
    public Iterable<String> splitByGuava() {
        return Splitter.on('，').split(testString);
    }

    /**
     * 查找子字符串索引（String）
     */
    @Benchmark
    public int indexOf() {
        return testString.indexOf("测试");
    }

    /**
     * 查找子字符串索引（Apache）
     */
    @Benchmark
    public int indexOfByApache() {
        return StringUtils.indexOf(testString, "测试");
    }

    /**
     * 查找子字符串索引（Guava）
     * 备注：该方法是对字符进行匹配
     */
    @Benchmark
    public int indexOfByGuava() {
        return CharMatcher.anyOf("测试").indexIn(testString);
    }
}
