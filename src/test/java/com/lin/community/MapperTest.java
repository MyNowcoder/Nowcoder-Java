package com.lin.community;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.pojo.User;
import com.lin.community.repository.DiscussPostMapper;
import com.lin.community.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    DiscussPostMapper mapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectDiscussPostRows(){
        int i = mapper.selectDiscussPostRows(0);
        System.out.println(i);
    }

    @Test
    public void testSelectDiscussPosts(){
        List<DiscussPost> discussPosts = mapper.selectDiscussPostsWithUser(0, 0, 10);
        System.out.println(discussPosts);
    }

    @Test
    public void testUser(){
        User system = userMapper.selectUserByEmailOrUsername("ggg1", "nowcoder24@sina.com");
        System.out.println(system);
    }
}
