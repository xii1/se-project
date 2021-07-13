package com.miu.se.common.response;

/**
 * @author duong
 */
public class ProcessPaymentResponse {
    private Long orderId;
    private String paymentGatewayUrl;

    public ProcessPaymentResponse(Long orderId, String paymentGatewayUrl) {
        this.orderId = orderId;
        this.paymentGatewayUrl = paymentGatewayUrl;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentGatewayUrl() {
        return paymentGatewayUrl;
    }

    public void setPaymentGatewayUrl(String paymentGatewayUrl) {
        this.paymentGatewayUrl = paymentGatewayUrl;
    }
}
