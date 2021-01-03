package com.fast.rabbitmq.util;

import java.util.UUID;

public class UUIDGenerator {

    public static String newUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
