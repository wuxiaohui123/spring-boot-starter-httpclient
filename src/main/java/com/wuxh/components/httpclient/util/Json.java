package com.wuxh.components.httpclient.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author wuxiaohui
 * @version 1
 * @description TODO
 * @date 2019/12/13 0013
 **/
public class Json {

    public static String encode(StringMap map) {
        return (new Gson()).toJson(map.map());
    }

    public static String encode(Object obj) {
        return (new GsonBuilder()).serializeNulls().create().toJson(obj);
    }

    public static <T> T decode(String json, Class<T> tClass) {
        return (new Gson()).fromJson(json, tClass);
    }

    public static <T> T decode(JsonElement jsonElement, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonElement, clazz);
    }

    public static StringMap decode(String json) {
        Type t = (new TypeToken<Map<String, Object>>() {}).getType();
        Map<String, Object> x = (Map)(new Gson()).fromJson(json, t);
        return new StringMap(x);
    }
}
