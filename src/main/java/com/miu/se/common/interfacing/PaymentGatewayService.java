package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Transaction;

public interface PaymentGatewayService {
    String getPaymentOrderUrl(Long orderId, double amount);
    boolean isValidTransaction(Transaction transaction);
}
