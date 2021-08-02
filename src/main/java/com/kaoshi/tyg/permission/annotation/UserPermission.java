package com.kaoshi.tyg.permission.annotation;

public enum UserPermission {

    SESSION(1),
    TOKEN(2),
    NOTMATCH(3);

    private Integer value;

    private UserPermission(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private UserPermission() {
    }
}
