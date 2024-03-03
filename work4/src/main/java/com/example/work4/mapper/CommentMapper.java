package com.example.work4.mapper;

import com.example.work4.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    void publish(Comment comment);
    void updateCommentnum(int article_id);
    void updateChild_count(int parent_id);
    List timeRank(int article_id);
}
