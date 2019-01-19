package com.example.util.操作word文档.freeMark;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 利用freemaker操作word
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/12/27 15:01
 */
public class ParseWord {
    private static String filePath; // 文件路径
    private static String fileName; // 文件名称
    private static String fileOnlyName; // 文件唯一名称

    public static void createWord(Map dataMap, String templateName, String filePath, String fileName) {
        try {
            // 创建配置实例
            Configuration configuration = new Configuration();

            // 设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl 模板文件统一放至 com.lun.templates 包下面
            configuration.setClassForTemplateLoading(ParseWord.class, "/");

            // 获取模板
            Template template = configuration.getTemplate(templateName);

            // 输出文件
            File outFile = new File(filePath + File.separator + fileName);

            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }

            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            // 生成文件
            template.process(dataMap, out);

            // 关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String createWord() {
        /** 用于组装 word 页面需要的数据 */
        Map<String, Object> dataMap = new HashMap<String, Object>();

        /** 组装数据 */
        dataMap.put("userName", "张三");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
        dataMap.put("currDate", sdf.format(new Date()));

        dataMap.put("content", "这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容这是其它内容");

        List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "标题" + i);
            map.put("content", "内容" + (i * 2));
            map.put("author", "作者" + (i * 3));
            newsList.add(map);
        }
        dataMap.put("newsList", newsList);

        /** 文件名称，唯一字符串 */
        Random r = new Random();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        StringBuffer sb = new StringBuffer();
        sb.append(sdf1.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));

        // 文件路径
        filePath = "";

        // 文件唯一名称
        fileOnlyName = "用 freemarker 导出的 Word 文档_" + sb + ".doc";

        ParseWord.createWord(dataMap, "templates/freemarker.ftl", filePath, fileOnlyName);

        return "createWordSuccess";
    }

    private static void generateDoc() {
        var docNum = new SimpleDateFormat("yyyyMMdd");
        var startTime = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        var affectionTime = new SimpleDateFormat("hh:mm");
        var now = new Date();

        var dataMap = new HashMap<>(16);
        dataMap.put("reportDate", new SimpleDateFormat("yyyy年MM月dd日").format(now));
        dataMap.put("reportNumber", docNum.format(now));
        dataMap.put("startExecutionTime", startTime.format(now));
        dataMap.put("stopExecutionTime", startTime.format(now));
        dataMap.put("affectionStartTime", affectionTime.format(now));
        dataMap.put("affectionStopTime", affectionTime.format(now));
        dataMap.put("project", getProject());

        var workers = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            var map = new HashMap<>(16);
            map.put("name", "余文彪");
            map.put("phone", "19927672430");
            map.put("remark", "研发");
            workers.add(map);
        }
        dataMap.put("workers", workers);

        filePath = "";
        fileOnlyName = "xxxx平台施工方案" + docNum.format(new Date()) + ".doc";

        ParseWord.createWord(dataMap, "templates/xxx平台施工方案模板.ftl", filePath, fileOnlyName);
    }

    private static String getProject() {
        var projectName = "1.xxxx运营管理平台\n";
        var projectRout = "xxxxxxx";
        return projectName + projectRout;
    }

    public static void main(String[] args) {
        generateDoc();
    }
}
