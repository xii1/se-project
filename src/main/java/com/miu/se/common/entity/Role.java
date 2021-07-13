package com.miu.se.common.entity;

import com.miu.se.common.interfacing.StringEnum;

public enum Role implements StringEnum {
    GUEST("GUEST"), BUYER("BUYER"), MODERATOR("MODERATOR"), ADMIN("ADMIN");
    String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getValue() {
        return role;
    }
}
