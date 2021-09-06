package com.lin.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscussPost {
    private Integer id;
    private String userId;
    private String title;
    private String content;
    private Short type;
    private Short status;
    private Date createTime;
    private Integer commentCount;
    private Double score;
}
