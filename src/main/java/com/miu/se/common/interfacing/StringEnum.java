package com.miu.se.common.interfacing;

import com.fasterxml.jackson.annotation.JsonValue;

public interface StringEnum {
    @JsonValue
    public String getValue();
}
