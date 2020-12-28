package com.fast.architecturedesignmode.singleton;

/**
 * 懒加载模式-线程安全-double check lock
 */
public class LazySingletonSafeDCL {
    private LazySingletonSafeDCL() {
    }

    private static volatile LazySingletonSafeDCL instance = null;

    public LazySingletonSafeDCL getInstance() {

        if (instance == null) {
            synchronized (LazySingletonSafeDCL.this) {
                if (instance == null) {
                    instance = new LazySingletonSafeDCL();
                }
            }
        }
        return instance;
    }
}
