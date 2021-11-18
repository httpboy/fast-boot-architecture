package com.example.braintree;

import lombok.Data;

@Data
public class PayDto {
    /**
     * 付款方式随机码
     */
    private String nonce;

    /**
     * 付款金额
     */
    private String amount;

    /**
     * 收集设备数据-谷歌支付、苹果支付
     */
    private String deviceData;


    /**
     * 邮政编码-谷歌支付、苹果支付
     */

    private String postalCode;

    /**
     * 官方生产的此交易的唯一编号
     */
    private String transactionId;

}
