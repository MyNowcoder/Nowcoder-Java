package com.lin.community.repository;

import com.lin.community.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    List<Comment>selectCommentByEntityWithUser(int entityId,int entityType,int offset,int limit);
    int selectCountByEntity(int entityId,int entityType);
}
