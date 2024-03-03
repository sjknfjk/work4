package com.example.work4.service;

import com.example.work4.domain.Article;
import com.example.work4.tool.Result;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {
    Result publish(HttpServletRequest request,String cover_url, String title, String content);
    Result clickRank(int size);
    Result timeRank(int size,int num);
    Result recommendRank(int size,int num);
    Result getInfo(String title);
    Result searchArticle(String keyword,int size,int num);
}
