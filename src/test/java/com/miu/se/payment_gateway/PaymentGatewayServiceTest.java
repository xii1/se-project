package com.miu.se.payment_gateway;

import com.miu.se.common.entity.Transaction;
import com.miu.se.common.interfacing.PaymentGatewayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author duong
 */
@Disabled
public class PaymentGatewayServiceTest {
    @Autowired
    PaymentGatewayService paymentGatewayService;

    @BeforeEach
    void setUp() {
        paymentGatewayService = new DefaultPaymentGatewayService();
    }

//    @Test
//    public void testGetPaymentOrderUrl() {
//        Long orderOne = (long) 1234;
//        String actualFirstUrl = paymentGatewayService.getPaymentOrderUrl(orderOne, 1000.0);
//        String expectedFirstUrl = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html?orderId=1234&amount=1000.0&orderInfo=ORDER%201234%20Amount%201000.0%20usd&returnUrl=http://www.shoppingapp.com/";
//        Assertions.assertEquals(actualFirstUrl, expectedFirstUrl);
//
//        Long orderTwo = (long)1345;
//        String actualSecondUrl = paymentGatewayService.getPaymentOrderUrl(orderTwo, 1500);
//        String expectedSecondUrl = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html?orderId=1345&amount=1500.0&orderInfo=ORDER%201345%20Amount%201500.0%20usd&returnUrl=http://www.shoppingapp.com/";
//        Assertions.assertEquals(actualSecondUrl, expectedSecondUrl);
//    }
//
//    @Test
//    public void testIsValidTransaction() {
//        Transaction firstTransaction = new Transaction();
//        firstTransaction.setId((long) 1234);
//        firstTransaction.setPaymentGateway("VNPay");
//        firstTransaction.setPaymentTransactionId("1234");
//        firstTransaction.setCreatedAt(new Date());
//        boolean isFirstTransactionValid = paymentGatewayService.isValidTransaction(firstTransaction);
//        Assertions.assertFalse(isFirstTransactionValid);
//
//        Transaction secondTransaction = new Transaction();
//        secondTransaction.setId((long)1235);
//        secondTransaction.setPaymentGateway("VNPay");
//        secondTransaction.setCreatedAt(new Date());
//        boolean isSecondTransactionValid = paymentGatewayService.isValidTransaction(secondTransaction);
//        Assertions.assertFalse(isSecondTransactionValid);
//
//        Transaction thirdTransaction = new Transaction();
//        thirdTransaction.setId((long) 1235);
//        thirdTransaction.setPaymentGateway("VNPay");
//        thirdTransaction.setPaymentTransactionId("VNP12345");
//        thirdTransaction.setCreatedAt(new Date());
//        boolean isThirdTransactionValid = paymentGatewayService.isValidTransaction(thirdTransaction);
//        Assertions.assertTrue(isThirdTransactionValid);
//    }
}
