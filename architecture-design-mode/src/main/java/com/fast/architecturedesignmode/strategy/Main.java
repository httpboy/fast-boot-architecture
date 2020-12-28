package com.fast.architecturedesignmode.strategy;

/**
 * 策略模式
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new AStrategy());
        context.show();

        Context context1 = new Context(new BStrategy());
        context1.show();

    }
}
