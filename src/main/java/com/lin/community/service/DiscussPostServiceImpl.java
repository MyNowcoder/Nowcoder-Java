package com.lin.community.service;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.repository.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired
    DiscussPostMapper discussPostMapper;

    @Override
    public DiscussPost getDiscussPostById(Integer id) {
        return discussPostMapper.selectById(id);
    }
}
