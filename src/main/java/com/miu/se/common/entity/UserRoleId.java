package com.miu.se.common.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private Long userId;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role);
    }
}
