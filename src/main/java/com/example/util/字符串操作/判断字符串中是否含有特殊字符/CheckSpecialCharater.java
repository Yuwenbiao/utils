package com.example.util.字符串操作.判断字符串中是否含有特殊字符;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java通过正则表达式判断字符串当中是否有特殊符号
 *
 * @author yuwb@corp.21cn.com
 * @date 19-6-21 上午10:03
 */
public class CheckSpecialCharater {
    public static void main(String[] args) {
        String str = "判断的字符串&&";
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println(m.find());
    }
}
