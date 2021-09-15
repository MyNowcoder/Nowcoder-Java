package com.lin.community.utils;

import com.lin.community.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息
 * @param
 * @return
 *
 */

@Component
public class HostUser {
    private ThreadLocal<User> threadLocalLocalUser=new ThreadLocal<>();

    public void setUser(User user) {
        this.threadLocalLocalUser.set(user);
    }

    public User getUser(){
        return this.threadLocalLocalUser.get();
    }

    public void clear(){
        this.threadLocalLocalUser.remove();
    }

}
