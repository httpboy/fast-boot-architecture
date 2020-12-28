package com.fast.architecturedesignmode.factory.simplefactory;

public class Factory {

    public static Product create(String key) {
        Product product = null;

        switch (key) {
            case "a":
                product = new AProduct();
                break;
            case "b":
                product = new BProduct();
                break;
            default:
                break;
        }
        return product;

    }
}
