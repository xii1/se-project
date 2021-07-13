package com.miu.se.common.response;

import com.miu.se.common.entity.Order;
import com.miu.se.common.entity.OrderItem;
import com.miu.se.common.entity.OrderStatus;
import com.miu.se.common.entity.ShippingAddress;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duong
 */
public class SaveOrderTransactionResponse {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String note;
    private boolean countedPoint = false;
    private List<OrderItem> items = new ArrayList<>();
    private ShippingAddress shippingAddress;

    /**
     * Get instance from order.
     * @param order order.
     * @return get instance from order.
     */
    public static SaveOrderTransactionResponse getInstanceFromOrder(Order order) {
        LocalDate setCreatedAt = order.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate setUpdatedAt = order.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        SaveOrderTransactionResponse response = new SaveOrderTransactionResponse();
        response.setId(order.getId());
        response.setOrderStatus(order.getStatus());
        response.setCreatedAt(setCreatedAt);
        response.setUpdatedAt(setUpdatedAt);
        response.setNote(order.getNote());
        response.setItems(order.getItems());
        //response.setShippingAddress(order.getShippingAddress());
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCountedPoint() {
        return countedPoint;
    }

    public void setCountedPoint(boolean countedPoint) {
        this.countedPoint = countedPoint;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
