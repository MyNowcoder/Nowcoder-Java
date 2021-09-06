package com.lin.community.controller;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.repository.DiscussPostMapper;
import com.lin.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscussPostController {

    @Autowired
    DiscussPostService service;

    @RequestMapping("/discussPost/{id}")
    DiscussPost getDiscussPostById(@PathVariable String id){
        return service.getDiscussPostById(Integer.parseInt(id));

    }
}
