package com.fast.architecturespringaop.dynamic.jdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonInvocationHandler implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前-开始找工作");
        Object invoke = method.invoke(target, args);
        System.out.println("调用后-发工资");

        return invoke;
    }
}
