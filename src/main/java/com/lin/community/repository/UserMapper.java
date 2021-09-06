package com.lin.community.repository;

import com.lin.community.pojo.User;

public interface UserMapper {
    User selectById(Integer id);
}
