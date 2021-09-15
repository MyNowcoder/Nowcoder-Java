package com.lin.community.repository;

import com.lin.community.pojo.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTicketMapper {
    @Insert("insert into login_ticket(user_id,ticket,status,expired) values(#{userId},#{ticket},#{status},#{expired})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select("select * from login_ticket where ticket=#{ticket}")
    LoginTicket selectLoginTicketByTicket(String ticket);

    @Update("update login_ticket set status=#{status} where ticket=#{ticket}")
    int updateLoginTicketStatusByTicket(Integer status,String ticket);
}
