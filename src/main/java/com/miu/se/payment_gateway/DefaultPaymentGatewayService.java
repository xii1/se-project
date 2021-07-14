package com.miu.se.payment_gateway;

import com.miu.se.common.entity.Transaction;
import com.miu.se.common.interfacing.PaymentGatewayService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

@Service
public class DefaultPaymentGatewayService implements PaymentGatewayService {
    @Override
    public String getPaymentOrderUrl(Long orderId, double amount) {
        UriBuilder builder = new DefaultUriBuilderFactory("http://sandbox.vnpayment.vn/paymentv2/vpcpay.html").builder();
        builder = builder.queryParam("orderId", orderId);
        builder = builder.queryParam("amount", amount);
        builder = builder.queryParam("orderInfo", "ORDER " + orderId + " Amount " + amount + " usd");
        builder = builder.queryParam("returnUrl", "http://www.shoppingapp.com/");
        return builder.build().toString();
    }

    @Override
    public boolean isValidTransaction(Transaction transaction) {
        // Todo: Need to implement 3rd validation in the future.
        // Stimulate VNPay validation.
        return transaction.getPaymentTransactionId() != null && transaction.getPaymentTransactionId().contains("VNP");
    }
}
