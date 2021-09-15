package com.lin.community.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.community.dto.CommentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    CommentService commentService;


    @Test
    void getCommentByEntityWithUser() throws JsonProcessingException {
        List<CommentInfo> commentByEntityWithUser = commentService.getCommentByEntityWithUser(232, 0, 100);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(commentByEntityWithUser);
        System.out.println(s);
    }

    @Test
    void getCountByEntity() {
    }
}