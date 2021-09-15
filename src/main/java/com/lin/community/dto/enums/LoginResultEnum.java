package com.lin.community.dto.enums;

public enum LoginResultEnum {
    USERNAME_NOT_EXIST(-1),
    USER_NOT_ACTIVATE(-2),
    PASSWORD_WRONG(-3),
    CODE_WRONG(-4),

    LOGIN_SUCCESS(0)
    ;

    private int type;

    LoginResultEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
