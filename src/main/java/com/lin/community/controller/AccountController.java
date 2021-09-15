package com.lin.community.controller;

import com.lin.community.annotation.LoginRequired;
import com.lin.community.service.UserService;
import com.lin.community.utils.HostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Value("${site.upload.path}")
    private String uploadPath;

    @Value("${site.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostUser hostUser;

    @LoginRequired
    @RequestMapping("/setting")
    public String getSettingPage(){
        return "/site/setting";
    }

}
