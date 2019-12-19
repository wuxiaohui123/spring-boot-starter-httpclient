package com.wuxh.components.httpclient.core;

import org.apache.http.client.methods.HttpRequestBase;

import java.io.InputStream;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/19 0019
 **/
public class HttpStreamResult {

    private InputStream in;

    private HttpRequestBase httpRequest;

    public InputStream getIn() {
        return in;
    }

    public HttpStreamResult setIn(InputStream in) {
        this.in = in;
        return this;
    }

    public HttpRequestBase getHttpRequest() {
        return httpRequest;
    }

    public HttpStreamResult setHttpRequest(HttpRequestBase httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }
}
