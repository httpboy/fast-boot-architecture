package com.fast.architecturespringaop.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前-开始找工作");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("调用后-发工资");
        return invoke;
    }
}
