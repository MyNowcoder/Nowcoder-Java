package com.lin.community.repository;

import com.lin.community.pojo.LoginTicket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginTicketMapperTest {
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    void insertLoginTicket() {
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setTicket("test");
        loginTicket.setExpired(new Date());
        loginTicket.setStatus(1);
        loginTicket.setUserId(117);
        loginTicketMapper.insertLoginTicket(loginTicket);
        System.out.println(loginTicket);
    }

    @Test
    void selectLoginTicketByTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectLoginTicketByTicket("test");
        System.out.println(loginTicket);
    }

    @Test
    void updateLoginTicketStatusByTicket() {
        loginTicketMapper.updateLoginTicketStatusByTicket(-1, "test");
    }
}