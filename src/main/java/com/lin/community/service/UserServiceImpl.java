package com.lin.community.service;

import com.lin.community.dto.enums.LoginResultEnum;
import com.lin.community.pojo.LoginTicket;
import com.lin.community.pojo.User;
import com.lin.community.dto.enums.ActivationResultEnum;
import com.lin.community.dto.enums.RegisterResultEnum;
import com.lin.community.repository.LoginTicketMapper;
import com.lin.community.repository.UserMapper;
import com.lin.community.utils.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static com.lin.community.dto.enums.RegisterResultEnum.*;
import static com.lin.community.dto.enums.ActivationResultEnum.*;
import static com.lin.community.dto.enums.LoginResultEnum.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${site.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User getUserById(Integer id){
        return userMapper.selectUserById(id);
    }

    public LoginTicket getLoginTicketByTicket(String loginTicket)
    {
        return loginTicketMapper.selectLoginTicketByTicket(loginTicket);
    }

    @Override
    public int updateUserHeaderUrl(Integer id,String path) {
        return userMapper.updateUserHeaderUrl(id, path);
    }

    @Override
    public RegisterResultEnum register(User user) {
        User forwardUser = userMapper.selectUserByEmailOrUsername(user.getUsername(), user.getEmail());
        //判断用户名或密码是否重复
        if(forwardUser!=null)
        {
            if(forwardUser.getUsername().equals(user.getUsername()))
                return USERNAME_INVALID;
            else
                return EMAIL_INVALID;
        }
        else{
            //插入用户
            String salt= UUID.randomUUID().toString().substring(0,5);
            user.setSalt(salt);
            user.setPassword(DigestUtils.md5DigestAsHex((salt+user.getPassword()).getBytes()));
            user.setActivationCode(UUID.randomUUID().toString());
            user.setCreateTime(new Date());
            user.setHeaderUrl("http://images.nowcoder.com/head/"+new Random().nextInt(1000)+"t.png");
            user.setType((short) 0);
            user.setStatus((short) 0);
            userMapper.insertUser(user);

            //发送激活邮件
            Context context=new Context();
            context.setVariable("email",user.getEmail());
            String url=domain+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode();
            context.setVariable("url",url);
            String process = templateEngine.process("/mail/activation", context);
            mailClient.sendMail(user.getEmail(),"牛客激活",process);
            return SUCCESS;
        }


    }

    @Override
    public ActivationResultEnum activation(Integer id,String activationCode) {
        User user = userMapper.selectUserById(id);
        if(user.getStatus()==(short)1)
            return ACTIVATION_REPEAT;
        else if(!activationCode.equals(user.getActivationCode()))
            return ACTIVATION_FAILURE;
        else {
            userMapper.updateUserStatusById((short) 1,id);
            return ACTIVATION_SUCCESS;
        }
    }

    /**
     * 验证账号与密码，返回登录结果
     * @param   [java.lang.String, java.lang.String, int, java.lang.String]
     * @return  com.lin.community.dto.enums.LoginResultEnum
     *
     */
    @Override
    public LoginResultEnum login(String username, String password, int expiredSecond,String ticket) {
        User user = userMapper.selectUserByEmailOrUsername(username, null);
        if(user==null)
            return USERNAME_NOT_EXIST;
        if(user.getStatus()==0)
            return USER_NOT_ACTIVATE;
        String s = DigestUtils.md5DigestAsHex((user.getSalt() + password).getBytes());
        if(!s.equals(user.getPassword()))
            return PASSWORD_WRONG;

        //生成登录凭证
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(ticket);
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+expiredSecond* 1000L));
        loginTicketMapper.insertLoginTicket(loginTicket);
        return LOGIN_SUCCESS;
    }

    @Override
    public void logout(String ticket) {
        loginTicketMapper.updateLoginTicketStatusByTicket(-1,ticket);
    }


}
