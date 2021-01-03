package com.fast.rabbitmq.builder;

import com.fast.rabbitmq.domain.MqMessage;

public class MqMessageBuilder {
    private String messageId;
    private String messageData;
    private long messageTimestamp;

    public MqMessageBuilder setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public MqMessageBuilder setMessageData(String messageData) {
        this.messageData = messageData;
        return this;
    }

    public MqMessageBuilder setMessageTimestamp(long messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
        return this;
    }

    public MqMessage create() {
        MqMessage mqMessage = new MqMessage();
        mqMessage.setMessageId(this.messageId);
        mqMessage.setMessageData(this.messageData);
        mqMessage.setMessageTimestamp(this.messageTimestamp);
        return mqMessage;
    }
}
