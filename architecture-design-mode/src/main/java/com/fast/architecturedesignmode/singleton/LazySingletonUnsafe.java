package com.fast.architecturedesignmode.singleton;

/**
 * 懒汉--线程不安全
 */
public class LazySingletonUnsafe {
    private static LazySingletonUnsafe instance = null;
    private LazySingletonUnsafe(){}

    public static LazySingletonUnsafe getInstance() {
        if(instance==null) {
            instance = new LazySingletonUnsafe();
        }
        return instance;

    }
}
