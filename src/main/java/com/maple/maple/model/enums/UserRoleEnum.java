package com.maple.maple.model.enums;

import lombok.Getter;

/**
 * 用户角色枚举
 */
@Getter
public enum UserRoleEnum {
    USER("用户", "user"),
    ADMIN("管理员", "admin");
    private final String text;
    private final String value;
    UserRoleEnum(String text, String value) {
        this.text=text;
        this.value=value;
    }
}
