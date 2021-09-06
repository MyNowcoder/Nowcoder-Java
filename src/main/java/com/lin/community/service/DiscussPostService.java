package com.lin.community.service;

import com.lin.community.pojo.DiscussPost;

import java.util.List;

public interface DiscussPostService {
    DiscussPost getDiscussPostById(Integer id);
    List<DiscussPost> getDiscussPosts(int offset,int limit);
    int getDiscussPostRows();
}
