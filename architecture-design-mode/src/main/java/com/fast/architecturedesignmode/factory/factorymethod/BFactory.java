package com.fast.architecturedesignmode.factory.factorymethod;

public class BFactory extends AbsFactory {
    @Override
    public Product create() {
        return new BProduct();
    }
}
