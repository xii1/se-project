package com.miu.se.common.request;

import com.miu.se.common.entity.Transaction;

/**
 * @author duong
 */
public class SaveOrderTransactionRequest {
    private Long userId;
    private Long orderId;
    private Transaction transaction;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
