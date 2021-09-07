package com.lin.community.controller;

import com.lin.community.dto.enums.RegisterResultEnum;
import com.lin.community.pojo.User;
import com.lin.community.dto.enums.ActivationResultEnum;
import com.lin.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.lin.community.dto.enums.RegisterResultEnum.*;
import static com.lin.community.dto.enums.ActivationResultEnum.*;
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    /**
     * 返回登录页面
     * @param   []
     * @return  java.lang.String
     *
     */
    @GetMapping("/register")
    public String getRegisterPage(){
        return "/site/register";
    }


    /**
     * 注册提交表单处理
     * @param   [org.springframework.ui.Model, com.lin.community.pojo.User]
     * @return  java.lang.String
     *
     */

    @PostMapping("/register")//注册表单提交
    public String Register(Model model, User user){
        RegisterResultEnum registerResult = userService.register(user);
        //注册成功
        if(registerResult==SUCCESS) {
            model.addAttribute("msg","注册成功，已经向您的邮箱发送激活邮件，请尽快激活");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }
        //注册失败
        else
            model.addAttribute("errType",registerResult.getType());
            return "/site/register";
    }

    /**
     * 激活邮件处理
     * @param   [java.lang.String, java.lang.String]
     * @return  java.lang.String
     *
     */
    @GetMapping("/activation/{id}/{activationCode}")
    public String activation(@PathVariable String activationCode, @PathVariable String id,Model model){

        ActivationResultEnum activationResult = userService.activation(Integer.parseInt(id), activationCode);
        if (activationResult ==ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
            model.addAttribute("target", "/login");
        } else if (activationResult == ACTIVATION_REPEAT) {
            model.addAttribute("msg", "无效操作,该账号已经激活过了!");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("msg", "激活失败,您提供的激活码不正确!");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }

}

