package com.miu.se.common.interfacing;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.ShippingAddress;
import com.miu.se.common.entity.Transaction;
import com.miu.se.common.response.ProcessPaymentResponse;

public interface CartService {
    ProcessPaymentResponse proceedPayment(Long userId, ShippingAddress shippingAddress) throws Exception;

    Order saveOrderTransaction(Long orderId, Transaction transaction) throws Exception;
}
