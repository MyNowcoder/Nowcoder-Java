package com.lin.community.dto.enums;

public enum CommentKindEnum {
    COMMENT_DISCUSS_POST(1),
    COMMENT_REPLY(2)
    ;
    private int type;

    CommentKindEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
