package com.lin.community.service;

import com.lin.community.dto.CommentInfo;
import com.lin.community.dto.enums.CommentKindEnum;
import com.lin.community.pojo.Comment;
import com.lin.community.repository.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.lin.community.dto.enums.CommentKindEnum.COMMENT_DISCUSS_POST;
import static com.lin.community.dto.enums.CommentKindEnum.COMMENT_REPLY;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentInfo> getCommentByEntityWithUser(int entityId, int offset, int limit) {
        //查询该文章的评论
        List<Comment> commentList = commentMapper.selectCommentByEntityWithUser(entityId, COMMENT_DISCUSS_POST.getType(), offset, limit);
        //DTO
        List<CommentInfo> commentInfoList=new ArrayList<>();
        for(Comment comment:commentList)
        {
            CommentInfo commentInfo=new CommentInfo();
            commentInfo.setComment(comment);
            //查询回复该评论的评论
            List<Comment> commentList1 = commentMapper.selectCommentByEntityWithUser(comment.getId(), COMMENT_REPLY.getType(), 0, Integer.MAX_VALUE);
            commentInfo.setCommentList(commentList1);
            commentInfoList.add(commentInfo);
        }
        return commentInfoList;
    }

    @Override
    public int getCountByEntity(int entityId, CommentKindEnum commentKind) {
        return commentMapper.selectCountByEntity(entityId,commentKind.getType());
    }
}
