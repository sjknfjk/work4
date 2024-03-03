package com.example.work4.service;

import com.example.work4.domain.Comment;
import com.example.work4.tool.Result;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {
    Result publish(HttpServletRequest request, int article_id,int parent_id,String content);
    Result timeRank(int article_id);
}
