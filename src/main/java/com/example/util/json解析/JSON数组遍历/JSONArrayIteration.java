package com.example.util.json解析.JSON数组遍历;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON数组遍历
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/11 18:03
 */
public class JSONArrayIteration {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", "jfkdsj");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject);

        forWay(jsonArray);
        lambdaWay(jsonArray);
    }

    /**
     * Lambda方式
     *
     * @param jsonArray JSON数组
     */
    private static void lambdaWay(JSONArray jsonArray) {
        jsonArray.forEach(System.out::println);
        jsonArray.forEach(o -> System.out.println(((JSONObject) o).getString("a")));
    }

    /**
     * for循环方式
     *
     * @param jsonArray JSON数组
     */
    private static void forWay(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            System.out.println(jsonObject.getString("a"));
        }
    }
}
