package com.wuxh.components.httpclient;

import com.wuxh.components.httpclient.core.HttpTemplate;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.net.www.http.HttpClient;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientAutoConfiguration {

    private HttpClientProperties httpClientProperties;

    public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }

    @Bean
    @ConditionalOnMissingBean(HttpClient.class)
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpClientProperties.getConnectTimeout())
                .setSocketTimeout(httpClientProperties.getSocketTimeout()).build();

        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setUserAgent(httpClientProperties.getAgent()).setMaxConnPerRoute(httpClientProperties.getMaxPerRoute())
                .setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
    }

    @Bean
    public HttpTemplate httpTemplate(CloseableHttpClient httpClient) {
        return new HttpTemplate(httpClient);
    }
}
