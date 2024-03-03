package com.example.work4.serviceImpl;

import com.example.work4.domain.Like;
import com.example.work4.domain.User;
import com.example.work4.tool.Result;
import com.example.work4.mapper.LikeMapper;
import com.example.work4.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Result updateLikenum(HttpServletRequest request, int k,int id) {
        Result result=new Result(-1,null,null);
        String token=request.getHeader("token");
        User user= (User) redisTemplate.opsForValue().get(token);
        switch (k) {
            case 1:
                try {
                    likeMapper.add(user.getId(),id,-1);
                    likeMapper.addArticleLike(id);
                    result.setMsg("点赞成功");
                    result.setCode(10000);
                } catch (Exception e) {
                    result.setMsg(e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    likeMapper.delete(user.getId(),id,-1);
                    likeMapper.cancelArticleLike(id);
                    result.setMsg("取消成功");
                    result.setCode(10000);
                } catch (Exception e) {
                    result.setMsg(e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    likeMapper.add(user.getId(),-1,id);
                    likeMapper.addCommentLike(id);
                    result.setMsg("点赞成功");
                    result.setCode(10000);
                } catch (Exception e) {
                    result.setMsg(e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    likeMapper.delete(user.getId(),-1,id);
                    likeMapper.cancelCommentLike(id);
                    result.setMsg("取消成功");
                    result.setCode(10000);
                } catch (Exception e) {
                    result.setMsg(e.getMessage());
                    e.printStackTrace();
                }
                break;
            default:
                result.setCode(-100);
                result.setMsg("指令错误");
        }
        return result;
    }
}
