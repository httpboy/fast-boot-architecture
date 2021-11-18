package com.example.braintree;


import com.alibaba.fastjson.JSON;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Description: 客户端支付
 * @Author: boyi.chen
 * @Date: 2021/10/27 15:00
 */
@RestController
@RequestMapping("/api/pay/")
public class BrainTreeController {
    private static final Logger LOG = LoggerFactory.getLogger(BrainTreeController.class);

    @Autowired
    CheckoutService checkoutService;

    /**
     * 获取客户端访问令牌
     *
     * @return
     */
    @PostMapping("getClientToken")
    public Object getClientToken() {
        String clientToken = checkoutService.getClientToken();
        return new ResponseEntity<>(clientToken, HttpStatus.OK);

    }

    /**
     * paypal支付
     *
     * @param payDto
     * @return
     */
    @PostMapping("/paypal/checkout")
    public Object paypalCheckOut(@RequestBody PayDto payDto) {
        BigDecimal decimalAmount = new BigDecimal(payDto.getAmount());
        String nonce = payDto.getNonce();
        String description = "test amount " + decimalAmount;
        String orderId = System.currentTimeMillis() + "";
        Result<Transaction> checkout = checkoutService.checkout(decimalAmount, nonce, description, orderId);
        LOG.info(JSON.toJSONString(checkout));
        return new ResponseEntity<>("付款成功", HttpStatus.OK);

    }

    /**
     * 谷歌支付
     *
     * @param payDto
     * @return
     */
    @PostMapping("/google/checkout")
    public Object googleCheckOut(@RequestBody PayDto payDto) {
        BigDecimal decimalAmount = new BigDecimal(payDto.getAmount());
        String nonce = payDto.getNonce();
        String orderId = System.currentTimeMillis() + "";
        String deviceData = payDto.getDeviceData();
        String postalCode = payDto.getPostalCode();
        Result<Transaction> checkout = checkoutService.googlePayCheckout(orderId, decimalAmount, nonce, deviceData, postalCode);
        LOG.info(JSON.toJSONString(checkout));
        return new ResponseEntity<>("退款成功", HttpStatus.OK);

    }

    /**
     * 苹果支付
     *
     * @param payDto
     * @return
     */
    @PostMapping("/apple/checkout")
    public Object appleCheckOut(@RequestBody PayDto payDto) {
        BigDecimal decimalAmount = new BigDecimal(payDto.getAmount());
        String nonce = payDto.getNonce();
        String orderId = System.currentTimeMillis() + "";
        String deviceData = payDto.getDeviceData();
        String postalCode = payDto.getPostalCode();
        Result<Transaction> checkout = checkoutService.applePayCheckout(orderId, decimalAmount, nonce, deviceData, postalCode);
        LOG.info(JSON.toJSONString(checkout));
        return new ResponseEntity<>("退款成功", HttpStatus.OK);

    }

    /**
     * 苹果支付
     *
     * @param payDto
     * @return
     */
    @PostMapping("/refund")
    public Object refund(@RequestBody PayDto payDto) {
        BigDecimal decimalAmount = new BigDecimal(payDto.getAmount());
        String transactionId = payDto.getTransactionId();
        boolean refund = checkoutService.refund(transactionId, decimalAmount);
        return new ResponseEntity<>("退款成功", HttpStatus.OK);


    }

    /**
     * 苹果支付
     *
     * @param payDto
     * @return
     */
    @PostMapping("/getTransactionDetail")
    public Object getTransactionDetail(@RequestBody PayDto payDto) {
        String transactionId = payDto.getTransactionId();
        Transaction transaction = checkoutService.getTransactionById(transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }


}
