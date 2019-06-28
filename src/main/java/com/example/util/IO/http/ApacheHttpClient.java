package com.example.util.IO.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Apache httpClient示例
 *
 * @author yuwb@corp.21cn.com
 * @date 19-6-26 上午11:44
 */
public class ApacheHttpClient {
    private static CloseableHttpClient httpClient = null;

    private static synchronized CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            //注册访问协议相关的Socket工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLConnectionSocketFactory.getSystemSocketFactory()).build();
            //HttpConnection 工厂 配置写请求/解析响应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);
            //dns解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //创建池化链接管理器
            PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
            //默认为Socket配置
            SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            manager.setDefaultSocketConfig(defaultSocketConfig);
            /*
             * 设置整个连接池的最大连接数
             * 每个路由的默认最大连接，每个路由实际最大连接数默认为DefaultMaxPerRoute控制，
             * 而MaxTotal是控制整个池子的最大数
             * 设置过小无法支持高并发 会有异常
             * ConnectionPoolTimeoutException：Timeout waiting for connection from pool
             * 路由是对MaxTotal的细分
             */
            manager.setMaxTotal(500);
            //设置每个路由的最大连接数
            manager.setDefaultMaxPerRoute(200);
            //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证 默认2s
            manager.setValidateAfterInactivity(5 * 1000);
            //默认请求配置,设置连接超时时间 2s,等待数据超时时间 5s,从连接池获取连接的等待超时时间
            RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(2 * 1000).setSocketTimeout(5 * 1000).setConnectionRequestTimeout(2000).build();
            //创建 HttpClient
            httpClient = HttpClients.custom().setConnectionManager(manager)
                    .setConnectionManagerShared(false)//连接池不是共享模式
                    .evictIdleConnections(1, TimeUnit.MINUTES)//定期回收空闲连接
                    .evictExpiredConnections()//定期回收过期连接
                    .setConnectionTimeToLive(1, TimeUnit.MINUTES) //连接存活时间，如果不设置，则根据长连接信息决定
                    .setDefaultRequestConfig(defaultRequestConfig) //设置默认请求配置
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE) //连接重用策略 是否能keepAlive
                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)//长连接配置，即获取长连接生产多长时间
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))//重试次数 默认3次 此处禁用
                    .build();
            //JVM停止或重启时 关闭连接池释放连接
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    httpClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));


        }
        return httpClient;
    }

    public static void main(String[] args) {
        HttpResponse response = null;
        try {
            HttpGet get = new HttpGet("https://blog.csdn.net/u014172271/article/details/80381640");
            response = getHttpClient().execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                EntityUtils.consume(response.getEntity());
            } else {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
