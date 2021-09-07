package com.lin.community.repository;

import com.lin.community.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserById(Integer id);

    User selectUserByEmailOrUsername(String username, String email);
    int updateUserStatusById(Short status,Integer id);
    int insertUser(User user);

}
