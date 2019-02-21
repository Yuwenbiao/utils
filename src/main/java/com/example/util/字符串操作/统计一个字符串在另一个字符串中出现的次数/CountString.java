package com.example.util.字符串操作.统计一个字符串在另一个字符串中出现的次数;

/**
 * 判断一个字符串在另一个字符串中出现的次数
 *
 * @author yuwb@corp.21cn.com
 * @date 19-2-21 下午4:40
 */
public class CountString {
    private static int getCount(String str, String key) {
        int count = 0;
        int index;
        //找到子串在字符串中出现的位置并截取后面的字符串
        while((index=str.indexOf(key))!=-1) {
            str = str.substring(index+key.length());
            count++;
        }
        return count;
    }
}
