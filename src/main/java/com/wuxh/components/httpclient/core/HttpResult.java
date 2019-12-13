package com.wuxh.components.httpclient.core;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
public class HttpResult {

    // 响应码
    private int code;

    // 响应体
    private String body;

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public HttpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpResult setBody(String body) {
        this.body = body;
        return this;
    }
}
