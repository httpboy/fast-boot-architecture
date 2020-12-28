package com.fast.architecturedesignmode.factory.simplefactory;

/**
 * 简单工厂
 */
public class Main {
    public static void main(String[] args) {
        Product aProduct = Factory.create("a");
        aProduct.show();

        Product bProduct = Factory.create("b");

        bProduct.show();

    }
}
