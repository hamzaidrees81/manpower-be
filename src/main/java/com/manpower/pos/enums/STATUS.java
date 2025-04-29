package com.manpower.pos.enums;

import lombok.Getter;

@Getter
public enum STATUS {
    ACTIVE("1"),
    INACTIVE("0");

    private final String value;

    STATUS(String value) {
        this.value = value;
    }

    public static STATUS from(String value) {
        for (STATUS status : STATUS.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + value);
    }

    public String toValue() {
        return value;
    }
}
