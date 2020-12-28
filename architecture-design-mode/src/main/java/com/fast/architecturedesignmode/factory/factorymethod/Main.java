package com.fast.architecturedesignmode.factory.factorymethod;

/**
 * 抽象工厂模式
 */
public class Main {
    public static void main(String[] args) {
        AbsFactory aFactory = new AFactory();
        Product product = aFactory.create();
        product.show();


        AbsFactory bFactory = new BFactory();
        Product product1 = bFactory.create();
        product1.show();
    }

}
