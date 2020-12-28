package com.fast.architecturespringaop.dynamic.jdk;

import com.fast.architecturespringaop.dynamic.cglib.PersonMethodInterceptor;
import com.fast.architecturespringaop.dynamic.jdk.Person;
import com.fast.architecturespringaop.dynamic.jdk.PersonImpl;
import com.fast.architecturespringaop.dynamic.jdk.PersonInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

public class Main {
    public static void main(String[] args) {
        PersonInvocationHandler handler = new PersonInvocationHandler();
        Person person = (Person) handler.bind(new PersonImpl());
        person.doWork();
    }
}
