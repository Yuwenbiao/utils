package com.example.util.文件操作.通过网络解析文件;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * 通过网络解析HTML文件
 *
 * @author yuwb@corp.21cn.com
 * @date 19-3-5 下午5:01
 */
public class Demo {
    private Document parseDoc(String path) throws IOException {

        String fileName = path + ".html";
        String url = fileName.replace("/home/btsp/spider/html/", "http://127.0.0.1:17879/");
        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);

        // 启用JS解释器，默认为true
        webClient.getOptions().setJavaScriptEnabled(true);
        // 禁用css支持
        webClient.getOptions().setCssEnabled(false);
        // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // 设置连接超时时间
        webClient.getOptions().setTimeout(3 * 1000);
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 等待js后台执行30秒
        webClient.waitForBackgroundJavaScript(1000);

        String pageAsXml = page.asXml();

        Document doc = Jsoup.parse(pageAsXml);
        return doc;
    }
}
