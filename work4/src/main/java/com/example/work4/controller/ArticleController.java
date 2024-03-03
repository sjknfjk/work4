package com.example.work4.controller;

import com.example.work4.domain.Article;
import com.example.work4.tool.Result;
import com.example.work4.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/article/publish")
    public Result publish(HttpServletRequest request,String cover_url, String title, String content){
        log.debug("发布文章");
        return articleService.publish(request,cover_url,title,content);
    }
    @RequestMapping("/article/popular")
    public Result clickRank(int size){
        log.debug("获取点击榜");
        return articleService.clickRank(size);
    }
    @RequestMapping("/article/new")
    public Result timeRank(int size,int num){
        log.debug("获取最新榜");
        return articleService.timeRank(size,num);
    }
    @RequestMapping("/article/recommend")
    public Result recommendRank(int size,int num){
        log.debug("获取推荐榜");
        return articleService.recommendRank(size,num);
    }
    @RequestMapping("/article/info")
    public Result getInfo(String title){
        log.debug("获取文章信息");
        return articleService.getInfo(title);
    }
    @RequestMapping("/article/search")
    public Result searchArticle(String keyword,int size,int num){
        log.debug("用关键字搜索文章");
        return articleService.searchArticle(keyword,size,num);
    }
}
