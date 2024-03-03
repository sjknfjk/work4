package com.example.work4.mapper;

import com.example.work4.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeMapper {
    void add(int user_id,int article_id, int comment_id);
    void delete(int user_id,int article_id, int comment_id);
    void addArticleLike(int id);
    void cancelArticleLike(int id);
    void addCommentLike(int id);
    void cancelCommentLike(int id);
}
