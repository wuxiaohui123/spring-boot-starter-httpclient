package com.wuxh.components.httpclient.core;

import com.wuxh.components.httpclient.util.Json;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
public class HttpTemplate {

    private CloseableHttpClient httpClient;

    public HttpTemplate(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * 不带参数的get请求
     *
     * @param url 请求url
     * @return 如果状态码为200，则返回body，如果不为200，则返回null
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        // 判断状态码是否为200
        String result = null;
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
        httpGet.releaseConnection();
        return result;
    }

    /**
     * 带参数的get请求
     *
     * @param url 请求url
     * @param map 请求参数
     * @return 如果状态码为200，则返回body，如果不为200，则返回null
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            map.forEach((k, v) -> uriBuilder.setParameter(k, StringUtils.isEmpty(v) ? "" : v.toString()));
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
    }

    /***
     * 带参数的post请求
     * 如果状态码为200，则返回 <T>，如果不为200，则返回null
     * @param url 请求url
     * @param map 请求参数集合
     * @param clazz 返回对象
     * @param <T> 返回对象类型
     * @return 返回指定对象
     * @throws Exception
     */
    public <T> T doPost(String url, Map<String, Object> map, Class<T> clazz) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            map.forEach((k, v) -> list.add(new BasicNameValuePair(k, v.toString())));
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, StandardCharsets.UTF_8);
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            return Json.decode(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), clazz);
        }
        httpPost.releaseConnection();
        return null;
    }

    /**
     * 带参数的post请求
     *
     * @param url 请求url
     * @param map 请求参数集合
     * @return httpResult
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            map.forEach((k, v) -> list.add(new BasicNameValuePair(k, v.toString())));
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, StandardCharsets.UTF_8);
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        httpPost.releaseConnection();
        return httpResult;
    }

    /**
     * 不带参数post请求
     *
     * @param url 请求参数
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /***
     *  发送POST 参数JSON
     * @param url 请求地址
     * @param json JSON参数
     * @return
     * @throws Exception
     */
    public HttpResult doPostJson(String url, String json) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (json != null) {
            // 把表单放到post里
            httpPost.setEntity(new StringEntity(json, "UTF-8"));
        }
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult httpResult = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        httpPost.releaseConnection();
        return httpResult;
    }
}
