package com.lin.community.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class LoginTicket {
    private Integer id;
    private Integer userId;
    private String ticket;
    private Integer status;
    private Date expired;
}
