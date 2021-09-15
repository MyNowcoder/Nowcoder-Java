package com.lin.community.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Comment {
    private Integer id;
    //发布人id
    private Integer userId;
    //评论发布人
    private User commentUser;
    //1代表是文章评论 2代表回复评论
    private Integer entityType;
    //评论对应的id 可能是文章 也可能是评论
    private Integer entityId;
    //回复对象id
    private Integer targetId;
    //回复对象
    private User targetUser;

    private String content;
    private Integer status;
    private Date createTime;


}
