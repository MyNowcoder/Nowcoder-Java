package com.lin.community.repository;

import com.lin.community.pojo.DiscussPost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussPostMapper {

    /**
     * 查询所有讨论帖，userId用于个人主页查询，后面两个用于分页
     * @param   [int, int, int]
     * @return  java.util.List<com.lin.community.pojo.DiscussPost>
     *
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * 查询表中有多少条数据
     * 注：如果在mapper.xml需要动态拼接条件<if>，而且该方法只有一个参数，那么必须要用@Param取别名
     * @param   [int]
     * @return  int
     *
     */
    int selectDiscussPostRows(@Param("userId")int userId);


    DiscussPost selectById(Integer id);
}
