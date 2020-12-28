package com.fast.architecturedesignmode.strategy;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void show() {
        strategy.show();
    }
}
