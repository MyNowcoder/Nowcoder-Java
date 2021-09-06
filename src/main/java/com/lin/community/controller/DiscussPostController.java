package com.lin.community.controller;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussPost")
public class DiscussPostController {

    @Autowired
    DiscussPostService service;

    @RequestMapping("/{id}")
    public DiscussPost getDiscussPostById(@PathVariable String id){
        return service.getDiscussPostById(Integer.parseInt(id));
    }

    @RequestMapping("/list/{offset}/{limit}")
    public List<DiscussPost> getDiscussPosts(@PathVariable String limit, @PathVariable String offset){
        return service.getDiscussPosts(Integer.parseInt(offset),Integer.parseInt(limit));
    }

    @RequestMapping("/rows")
    public int getDiscussPostRows(){
        return service.getDiscussPostRows();
    }

}
