package com.example.braintree;

import com.alibaba.fastjson.JSON;
import com.braintreegateway.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Davis on 16/12/27.
 */
@Service
public class CheckoutService {
    /**
     * log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    private BraintreeGateway gateway;

    public String getClientToken() {
        String clientToken = gateway.clientToken().generate();
        return clientToken;
    }

    public Result<Transaction> checkout(BigDecimal decimalAmount, String nonce, String description, String orderId) {
        TransactionRequest request = new TransactionRequest()
                .amount(decimalAmount)
                //指定货币
//                .merchantAccountId("USD")
                .paymentMethodNonce(nonce)
                .orderId(orderId)
                .options()
                .paypal()
                .description(description)
                .done()
                .submitForSettlement(true)
                .done();
        Result<Transaction> result = gateway.transaction().sale(request);
        return result;
    }

    public Result<Transaction> googlePayCheckout(String orderId, BigDecimal amount, String nonce, String deviceData, String postalCode) {
        TransactionRequest request = new TransactionRequest()
                .amount(amount)
                //指定货币
//                .merchantAccountId("USD")
                .paymentMethodNonce(nonce)
                .orderId(orderId)
                .deviceData(deviceData)
                .options()
                .submitForSettlement(true)
                .done()
                .billingAddress()
                .postalCode(postalCode)
                .done();
        Result<Transaction> result = gateway.transaction().sale(request);
        return result;
    }

    public Result<Transaction> applePayCheckout(String orderId, BigDecimal amount, String nonce, String deviceData, String postalCode) {
        TransactionRequest request = new TransactionRequest()
                .amount(amount)
                //指定货币
//                .merchantAccountId("USD")
                .paymentMethodNonce(nonce)
                .orderId(orderId)
                .deviceData(deviceData)
                .options()
                .submitForSettlement(true)
                .done()
                .billingAddress()
                .postalCode(postalCode)  //邮编
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);
        return result;
    }

    /**
     * 获取交易信息
     *
     * @param transactionId
     * @return
     */
    public Transaction getTransactionById(String transactionId) {
        return gateway.transaction().find(transactionId);
    }

    /**
     * 退款
     *
     * @param transactionId
     * @param amount
     * @return
     */
    public boolean refund(String transactionId, BigDecimal amount) {
        Transaction transaction = gateway.transaction().find(transactionId);
        if (transaction.getStatus() == Transaction.Status.SETTLED ||
                transaction.getStatus() == Transaction.Status.SUBMITTED_FOR_SETTLEMENT) {
            //发起退款
            Result<Transaction> result = gateway.transaction().refund(transactionId, amount);
            if (result.isSuccess()) {
                LOG.info("refund transactionId " + transactionId + " " + JSON.toJSONString(result));
                return true;
            } else {
                LOG.error("refund transactionId " + transactionId + " " + JSON.toJSONString(result));
                return false;
            }
        } else if (transaction.getStatus() == Transaction.Status.AUTHORIZED ||
                transaction.getStatus() == Transaction.Status.SUBMITTED_FOR_SETTLEMENT ||
                (transaction.getPaymentInstrumentType() == PaymentInstrumentType.PAYPAL_ACCOUNT
                        && transaction.getStatus() == Transaction.Status.SETTLEMENT_PENDING)) {
            //交易作废，同时退款给用户
            Result<Transaction> result = gateway.transaction().voidTransaction(transactionId);
            if (result.isSuccess()) {
                LOG.info("voidTransaction transactionId " + transactionId + " " + JSON.toJSONString(result));
                return true;
            } else {
                LOG.error("voidTransaction transactionId " + transactionId + " " + JSON.toJSONString(result));
                return false;
            }
        }
        return false;

    }
}
