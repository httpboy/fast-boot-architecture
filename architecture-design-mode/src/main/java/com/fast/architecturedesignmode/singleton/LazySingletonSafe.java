package com.fast.architecturedesignmode.singleton;

/**
 * 优点：实现了懒加载的效果，线程安全。
 * 缺点：使用synchronized会造成不必要的同步开销，而且大部分时候我们是用不到同步的。
 */
public class LazySingletonSafe {
    private LazySingletonSafe() {

    }

    private static LazySingletonSafe instance = null;

    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonSafe();
        }

        return instance;

    }
}
