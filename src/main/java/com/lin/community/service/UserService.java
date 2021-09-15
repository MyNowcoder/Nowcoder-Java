package com.lin.community.service;

import com.lin.community.dto.enums.ActivationResultEnum;
import com.lin.community.dto.enums.LoginResultEnum;
import com.lin.community.dto.enums.RegisterResultEnum;
import com.lin.community.pojo.LoginTicket;
import com.lin.community.pojo.User;

public interface UserService {
    User getUserById(Integer id);
    LoginTicket getLoginTicketByTicket(String loginTicket);
    int updateUserHeaderUrl(Integer id,String path);
    RegisterResultEnum register(User user);
    ActivationResultEnum activation(Integer id, String activationCode);
    LoginResultEnum login(String username,String password,int expiredSecond,String ticket);
    void logout(String ticket);
}
