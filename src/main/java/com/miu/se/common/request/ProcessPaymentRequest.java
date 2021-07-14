package com.miu.se.common.request;

import com.miu.se.common.entity.ShippingAddress;

/**
 * @author duong
 */
public class ProcessPaymentRequest {
    private Long userId;
    private ShippingAddress shippingAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
