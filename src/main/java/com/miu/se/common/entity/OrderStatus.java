package com.miu.se.common.entity;

import com.miu.se.common.interfacing.StringEnum;

public enum OrderStatus implements StringEnum {
    WAITFORPAYMENT("WAITFORPAYMENT"), PROCESSING("PROCESSING"), CANCELLED("CANCELLED"), CONFIRMED("CONFIRMED"), SHIPPED("SHIPPED"), DELIVERED("DELIVERED");

    String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
