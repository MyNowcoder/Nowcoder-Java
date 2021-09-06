package com.lin.community.service;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.repository.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired
    DiscussPostMapper discussPostMapper;

    @Override
    public DiscussPost getDiscussPostById(Integer id) {
        return discussPostMapper.selectById(id);
    }

    @Override
    public List<DiscussPost> getDiscussPosts(int offset, int limit) {
        return discussPostMapper.selectDiscussPostsWithUser(offset,limit);
    }

    @Override
    public int getDiscussPostRows() {
        return discussPostMapper.selectDiscussPostRows();
    }
}
