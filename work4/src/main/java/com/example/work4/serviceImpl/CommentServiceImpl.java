package com.example.work4.serviceImpl;

import com.example.work4.domain.Comment;
import com.example.work4.domain.User;
import com.example.work4.tool.Result;
import com.example.work4.mapper.CommentMapper;
import com.example.work4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Result publish(HttpServletRequest request, int article_id,int parent_id,String content) {
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            Comment comment=new Comment(user.getId(),article_id,parent_id,content);
            if (comment.getParent_id() != -1 && comment.getArticle_id() == -1) {
                commentMapper.publish(comment);
                commentMapper.updateChild_count(comment.getParent_id());
                result.setMsg("发布成功");
                result.setCode(10000);
                result.setData(comment);
            }else if (comment.getParent_id()==-1&&comment.getArticle_id()!=-1){
                commentMapper.publish(comment);
                commentMapper.updateCommentnum(comment.getArticle_id());
                result.setMsg("发布成功");
                result.setCode(10000);
                result.setData(comment);
            }else {
                result.setMsg("错误：文章id或评论id有误");
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result timeRank(int article_id) {
        Result result=new Result(-1,null,null);
        try {
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(commentMapper.timeRank(article_id).toArray(new Comment[8]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}

