package com.miu.se.common.entity;

import com.miu.se.common.interfacing.StringEnum;

public enum UserStatus implements StringEnum {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    String value;

    UserStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
