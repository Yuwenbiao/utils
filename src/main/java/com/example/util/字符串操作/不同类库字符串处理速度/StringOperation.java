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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 字符串处理测试
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-1 下午4:57
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(2)
@Threads(Threads.MAX)
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 3, time = 3)
public class StringOperation {
    private static String testString;
    private static String testString2;

    /**
     * 进行字符串的初始化
     */
    @Setup
    public void prepare() {
        try (Stream<String> lines = Files.lines(Paths.get("testString"), Charset.defaultCharset())) {
            List<String> stringList = lines.collect(toList());
            testString = stringList.get(0);
            testString2 = stringList.get(1);
            System.out.println("字符串测试开始：testString长度=" + testString.length() + "，testString2长度=" + testString2.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
     * 字符替换（String）
     */
    @Benchmark
    public String replaceChar() {
        return testString.replace('测', '试');
    }

    /**
     * 字符替换（Apache）
     */
    @Benchmark
    public String replaceCharByApache() {
        return StringUtils.replace(testString, "测", "试");
    }

    /**
     * 字符替换（Guava）
     */
    @Benchmark
    public String replaceCharByGuava() {
        return CharMatcher.is('测').replaceFrom(testString, '试');
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
     * 字符串比较（String）
     */
    @Benchmark
    public int compareTo() {
        return testString.compareTo(testString2);
    }

    /**
     * 字符串比较（Apache）
     */
    @Benchmark
    public int compareToByApache() {
        return StringUtils.compare(testString, testString2);
    }

    /**
     * 测试此字符串是否以指定的后缀结束（String）
     */
    @Benchmark
    public boolean endsWith() {
        return testString.endsWith("这是一段字符串");
    }

    /**
     * 测试此字符串是否以指定的后缀结束（Apache）
     */
    @Benchmark
    public boolean endsWithByApache() {
        return StringUtils.endsWith(testString, "这是一段字符串");
    }

    /**
     * 判断字符串是否相等（String）
     */
    @Benchmark
    public boolean testEquals() {
        return testString.equals(testString2);
    }

    /**
     * 判断字符串是否相等（Apache）
     */
    @Benchmark
    public boolean testEqualsByApache() {
        return StringUtils.equals(testString, testString2);
    }

    /**
     * 返回指定字符在此字符串中最后一次出现处的索引（String）
     */
    @Benchmark
    public int lastIndexOfChar() {
        return testString.lastIndexOf('串');
    }

    /**
     * 返回指定字符在此字符串中最后一次出现处的索引（Apache）
     */
    @Benchmark
    public int lastIndexOfCharByApache() {
        return StringUtils.lastIndexOf(testString, '串');
    }

    /**
     * 返回指定字符在此字符串中最后一次出现处的索引（Guava）
     */
    @Benchmark
    public int lastIndexOfCharByGuava() {
        return CharMatcher.is('串').lastIndexIn(testString);
    }

    /**
     * 返回一个新字符串（String）
     */
    @Benchmark
    public String substring() {
        return testString.substring(2, 20);
    }

    /**
     * 返回一个新字符串（Apache）
     */
    @Benchmark
    public String substringByApache() {
        return StringUtils.substring(testString, 2, 20);
    }
}
