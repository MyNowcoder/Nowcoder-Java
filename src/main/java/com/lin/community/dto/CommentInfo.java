package com.lin.community.dto;

import com.lin.community.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentInfo {
    //评论本身
    private Comment comment;
    //回复评论的评论
    private List<Comment> commentList;
}
