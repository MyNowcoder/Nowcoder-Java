package com.lin.community.controller;

import com.lin.community.pojo.DiscussPost;
import com.lin.community.pojo.dto.Page;
import com.lin.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiscussPostController {

    @Autowired
    DiscussPostService service;

    @RequestMapping("/index")
    public String getDiscussPosts(Model model, Page page){
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        page.setRows(service.getDiscussPostRows());
        page.setPath("/index");

        List<DiscussPost> discussPosts = service.getDiscussPosts(page.getOffset(), page.getLimit());
        model.addAttribute("list",discussPosts);
        return "/index";
    }

    @RequestMapping("/rows")
    public int getDiscussPostRows(){
        return service.getDiscussPostRows();
    }

}
