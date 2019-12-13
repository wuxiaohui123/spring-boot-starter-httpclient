package com.wuxh.components.httpclient.util;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
public class StringMap {

    private Map<String, Object> map;

    public StringMap() {
        this(new HashMap());
    }

    public StringMap(Map<String, Object> map) {
        this.map = map;
    }

    public StringMap put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public StringMap putNotEmpty(String key, String value) {
        if (!StringUtils.hasText(value)) {
            this.map.put(key, value);
        }

        return this;
    }

    public StringMap putNotNull(String key, Object value) {
        if (value != null) {
            this.map.put(key, value);
        }

        return this;
    }

    public StringMap putWhen(String key, Object val, boolean when) {
        if (when) {
            this.map.put(key, val);
        }

        return this;
    }

    public StringMap putAll(Map<String, Object> map) {
        this.map.putAll(map);
        return this;
    }

    public StringMap putAll(StringMap map) {
        this.map.putAll(map.map);
        return this;
    }

    public void forEach(StringMap.Consumer imp) {
        Iterator var2 = this.map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> i = (Map.Entry)var2.next();
            imp.accept((String)i.getKey(), i.getValue());
        }

    }

    public int size() {
        return this.map.size();
    }

    public Map<String, Object> map() {
        return this.map;
    }

    public Object get(String key) {
        return this.map.get(key);
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public String formString() {
        final StringBuilder b = new StringBuilder();
        this.forEach(new StringMap.Consumer() {
            private boolean notStart = false;

            @Override
            public void accept(String key, Object value) {
                if (this.notStart) {
                    b.append("&");
                }

                try {
                    b.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(value.toString(), "UTF-8"));
                } catch (UnsupportedEncodingException var4) {
                    throw new AssertionError(var4);
                }

                this.notStart = true;
            }
        });
        return b.toString();
    }

    public String jsonString() {
        return Json.encode(this);
    }

    public interface Consumer {
        void accept(String var1, Object var2);
    }

}
