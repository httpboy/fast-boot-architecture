package com.fast.architecturedesignmode.singleton;

/**
 * 饿汉式
 */
public class EagerSingleton {
    private EagerSingleton() {
    }

    private static EagerSingleton instance = new EagerSingleton();


    public static EagerSingleton getInstance() {

        return instance;
    }
}
