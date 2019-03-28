package com.example.util.IO.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * HTTP请求工具
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-26 下午4:38
 */
@Slf4j
public class HttpRequestUtils {
    /**
     * 发起POST请求
     *
     * @param strURL 请求URL
     * @param params 请求参数
     * @return 请求结果
     */
    public static String post(String strURL, Map<String, String> params) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            out.append(JSONObject.toJSONString(params));
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            InputStream is;
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = connection.getContentLength();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                return new String(data, StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            log.error("Exception occur when send http post request!", e);
        }
        return "error";
    }
}
