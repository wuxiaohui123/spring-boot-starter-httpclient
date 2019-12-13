package com.wuxh.components.httpclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.http.client")
public class HttpClientProperties {

    /**
     * 获取连接超时时间
     */
    private int connectTimeout = 1000;

    /**
     * 连接超时时间
     */
    private int socketTimeout = 10000;

    /**
     * 单个路由并数
     */
    private int maxPerRoute = 10;


    /**
     * 最大连接数
     */
    private int maxTotal = 50;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }
}
