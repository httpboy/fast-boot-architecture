package com.fast.architecturedesignmode.factory.factorymethod;

public class AFactory extends AbsFactory {
    @Override
    public Product create() {
        return new AProduct();
    }
}
