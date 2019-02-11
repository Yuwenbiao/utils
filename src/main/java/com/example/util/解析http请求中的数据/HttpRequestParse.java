package com.example.util.解析http请求中的数据;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析HTTP请求中的数据
 *
 * @author yuwb@corp.21cn.com
 * @date 2019/2/11 10:30
 */
public class HttpRequestParse {

    private static int parseMobilicationParameters(HttpServletRequest request) {
        //获取body中的数据
        String requestBody = getRequestString(request);
        //获取header中的数据
        String token = request.getHeader("X-Access-Token");
        Map<String, Object> parameters = new HashMap<>(JSONObject.parseObject(requestBody));
        parameters.put("token", token);
        request.setAttribute("requestParas", parameters);
        return 0;
    }

    /**
     * 此处研究一下是否可以把输入流转换成stream流
     */
    private static String getRequestString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (ServletInputStream sis = request.getInputStream(); InputStreamReader ir = new InputStreamReader(sis, StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(ir)) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
