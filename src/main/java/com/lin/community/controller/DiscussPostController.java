package com.lin.community.controller;

import com.lin.community.annotation.LoginRequired;
import com.lin.community.dto.CommentInfo;
import com.lin.community.dto.Page;
import com.lin.community.pojo.DiscussPost;
import com.lin.community.pojo.User;
import com.lin.community.service.CommentService;
import com.lin.community.service.DiscussPostService;
import com.lin.community.utils.HostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import static com.lin.community.dto.enums.CommentKindEnum.COMMENT_DISCUSS_POST;

@Controller
public class DiscussPostController {

    @Autowired
    DiscussPostService discussPostService;

    @Autowired
    HostUser hostUser;

    @Autowired
    CommentService commentService;

    /**
     * 实现首页列表功能
     * @param   [org.springframework.ui.Model, com.lin.community.dto.Page]
     * @return  java.lang.String
     *
     */
    @RequestMapping("/index")
    public String getDiscussPosts(Model model, Page page){
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        page.setRows(discussPostService.getDiscussPostRows());
        page.setPath("/index");
        List<DiscussPost> discussPosts = discussPostService.getDiscussPosts(page.getOffset(), page.getLimit());
        model.addAttribute("list",discussPosts);
        return "/index";
    }

    /**
     * 发布帖子
     * @param   [java.lang.String, java.lang.String]
     * @return  java.lang.String
     *
     */
    @LoginRequired
    @PostMapping("/discussPost")
    public String addDiscussPost(String title,String context){
        User user = hostUser.getUser();
        DiscussPost discussPost=new DiscussPost();
        discussPost.setUserId(user.getId().toString());
        discussPost.setTitle(title);
        discussPost.setContent(context);
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);
        return "redirect:/index";
    }

    /**
     * 帖子详情
     * @param   [int, org.springframework.ui.Model]
     * @return  java.lang.String
     *
     */
    @GetMapping("/discussPost/detail/{id}")
    public String showDiscussPost(@PathVariable int id,Model model,Page page){
        //帖子内容
        DiscussPost discussPostById = discussPostService.getDiscussPostById(id);
        model.addAttribute("discussPost",discussPostById);
        //帖子评论分页信息
        page.setLimit(5);
        page.setPath("/discussPost/detail/"+id);
        page.setRows(commentService.getCountByEntity(id,COMMENT_DISCUSS_POST));
        //帖子评论信息
        List<CommentInfo> commentByEntityWithUser = commentService.getCommentByEntityWithUser(id, page.getOffset(), page.getLimit());
        model.addAttribute("commentInfoList",commentByEntityWithUser);


        return "site/discuss-detail";

    }

}
