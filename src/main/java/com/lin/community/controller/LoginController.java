package com.lin.community.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lin.community.annotation.LoginRequired;
import com.lin.community.dto.enums.LoginResultEnum;
import com.lin.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import static com.lin.community.dto.enums.LoginResultEnum.*;


@Controller
public class LoginController {

    private static final Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/login")
    public String getLoginPage(){
        return "/site/login";
    }

    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletResponse response,HttpSession session){
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        //将验证码字符串存入session
        session.setAttribute("kaptcha",text);
        //将验证码图片输出到浏览器
        response.setContentType("image/png");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
           log.error("验证码生成失败"+e.getMessage());
        }


    }

    @PostMapping("/login")
    public String login(String username, String password, String code, boolean remember, HttpSession session, HttpServletResponse response, Model model, HttpServletRequest request){

        System.out.println(Thread.currentThread().getName());

        //通过session获取已经存在的验证码字符串,检查验证码
        String kaptcha = (String) session.getAttribute("kaptcha");
        if(kaptcha==null||!kaptcha.equalsIgnoreCase(code))
        {
            model.addAttribute("type",CODE_WRONG.getType());
            return "/site/login";
        }
        String ticket= UUID.randomUUID().toString();
        //调用Service层，验证账户信息
        LoginResultEnum loginResult = userService.login(username, password, 3600 * 3,ticket);
        if(loginResult==USERNAME_NOT_EXIST||loginResult==USER_NOT_ACTIVATE||loginResult==PASSWORD_WRONG)
        {
            model.addAttribute("type",loginResult.getType());
            return "/site/login";
        }
        else {
            //验证成功，将登录凭证存入cookie,并且重定向首页
            Cookie cookie=new Cookie("ticket",ticket);
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(3600*3);
            response.addCookie(cookie);
            return "redirect:/index";
        }
    }

    @LoginRequired
    @RequestMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/index";
    }

}
