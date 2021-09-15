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
        return discussPostMapper.selectDiscussPostByIdWithUser(id);
    }

    @Override
    public List<DiscussPost> getDiscussPosts(int offset, int limit) {
        return discussPostMapper.selectDiscussPostsWithUser(offset,limit);
    }

    @Override
    public int getDiscussPostRows() {
        return discussPostMapper.selectDiscussPostRows();
    }

    @Override
    public int addDiscussPost(DiscussPost discussPost) {
        //TODO:过滤敏感词、转义HTML
        return discussPostMapper.insertDiscussPost(discussPost);
    }
}
