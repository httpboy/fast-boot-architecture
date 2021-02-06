package com.fast.architecturespringaop.dynamic.cglib;


import net.sf.cglib.proxy.Enhancer;

public class Main {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new PersonMethodInterceptor());

        Person person = (Person) enhancer.create();
        person.doWork();
    }
}