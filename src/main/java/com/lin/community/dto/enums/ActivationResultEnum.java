package com.lin.community.dto.enums;

public enum ActivationResultEnum {
    ACTIVATION_REPEAT(-1),
    ACTIVATION_SUCCESS(0),
    ACTIVATION_FAILURE(-2)
    ;

    private int type;

    ActivationResultEnum(int type) {

        this.type = type;
    }

    public int getType() {
        return type;
    }
}
