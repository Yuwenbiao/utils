package com.example.util.json解析.JSON和集合互相转换;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON和集合互相转换
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/20 13:18
 */
public class Json2List {
    public static void main(String[] args) {
        json2List();
    }

    private static void json2List() {
        List<String> list = new ArrayList<>();
        list.add("test");

        String jsonArray = JSONArray.toJSONString(list);
        System.out.println(jsonArray);

        List<String> json2List = JSONArray.parseArray(jsonArray, String.class);
        System.out.println(json2List.get(0));
    }
}
