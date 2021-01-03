package com.fast.rabbitmq.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 案例演示方便，就这样写，生产项目应该用redis存储
 */
public class CacheUtil {
    public static ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap(16);


    public static String get(String key) {
        return hashMap.get(key);
    }

    public static void set(String key, String value) {
        hashMap.put(key, value);
    }

    public static void remove(String key){

        hashMap.remove(key);
    }
}
