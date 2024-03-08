package com.example.work4.serviceImpl;

import com.example.work4.domain.Article;
import com.example.work4.domain.User;
import com.example.work4.mapper.LikeMapper;
import com.example.work4.tool.Result;
import com.example.work4.mapper.ArticleMapper;
import com.example.work4.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Result publish(HttpServletRequest request,String cover_url, String title, String content){
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            Article article=new Article(user.getUsername(), cover_url,title,content);
            articleMapper.publish(article);
            result.setMsg("发布成功");
            result.setCode(10000);
            result.setData(article);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result clickRank(int size){
        Result result=new Result(-1,null,null);
        try {
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(articleMapper.clickRank(size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result timeRank(int size,int num){
        Result result=new Result(-1,null,null);
        try {
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(articleMapper.timeRank(size,(num-1)*size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result recommendRank(int size,int num){
        Result result=new Result(-1,null,null);
        try {
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(articleMapper.recommendRank(size,(num-1)*size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result getInfo(String title){
        Result result=new Result(-1,null,null);
        try {
            Article article= articleMapper.getInfo(title);
            if (article == null) {
                result.setMsg("文章不存在");
            }else {
                articleMapper.updateClick(article.getId());
                result.setMsg("获取成功");
                result.setCode(10000);
                result.setData(articleMapper.getInfo(title));
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result searchArticle(String keyword,int size,int num){
        Result result=new Result(-1,null,null);
        try {
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(articleMapper.searchArticle(keyword,size,(num-1)*size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
