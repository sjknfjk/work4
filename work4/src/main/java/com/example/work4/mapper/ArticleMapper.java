package com.example.work4.mapper;

import com.example.work4.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    void publish(Article article);
    List clickRank(int size);//按点击量降序并显示前size项，不可翻页
    List timeRank(int size,int num);
    List recommendRank(int size,int num);//按点击量降序并显示第num页的数据
    Article getInfo(String title);
    void updateClick(int id);
    List searchArticle(String keyword,int size,int num);
}
