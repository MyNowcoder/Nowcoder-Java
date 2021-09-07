package com.lin.community.dto.enums;

public enum RegisterResultEnum {

    USERNAME_INVALID(-1,"用户名已存在"),
    EMAIL_INVALID(-2,"邮箱已存在"),
    SUCCESS(0,"注册成功");

    private int type;
    private String errMsg;

    RegisterResultEnum(int type, String errMsg) {
        this.type = type;
        this.errMsg = errMsg;
    }

    public int getType() {
        return type;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
