package com.example.work4.controller;

import com.example.work4.tool.Result;
import com.example.work4.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment/publish")
    public Result publish(HttpServletRequest request, int article_id,int parent_id,String content){
        log.info("发布评论");
        return commentService.publish(request,article_id,parent_id,content);
    }
    @RequestMapping("/comment/list")
    public Result timeRank(int article_id){
        log.debug("按时间顺序获取评论列表");
        return commentService.timeRank(article_id);
    }
}
