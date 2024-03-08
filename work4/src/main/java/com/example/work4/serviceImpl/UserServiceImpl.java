package com.example.work4.serviceImpl;

import ch.qos.logback.core.util.FileUtil;
import com.example.work4.domain.Article;
import com.example.work4.domain.User;
import com.example.work4.mapper.UserMapper;
import com.example.work4.tool.Result;
import com.example.work4.service.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public Result regist(String username,String password){
        Result result=new Result(-1,null,null);
        try {
            //缺少判断输入的用户名和密码的合法性
            User user=userMapper.getInfoByName(username);
            if (user != null) {
                result.setMsg("用户名已存在");
            }else {
                userMapper.regist(username,password);
                result.setMsg("注册成功");
                result.setCode(10000);
                result.setData(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
            return result;
    }

    @Override
    public Result login(String username, String password) {
        Result result=new Result(-1,null,null);
        try {
            User user=userMapper.login(username, password);
            if (user==null) {
                result.setMsg("用户名或密码错误");
            }else {
                String token= UUID.randomUUID()+"";
                redisTemplate.opsForValue().set(token,user, Duration.ofMinutes(30L));
                result.setMsg("登陆成功");
                result.setCode(10000);
                result.setData(token);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result getInfo(HttpServletRequest request) {
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            Object user=redisTemplate.opsForValue().get(token);
            if (user==null) {
                result.setMsg("获取信息失败");
            }else {
                result.setMsg("获取信息成功");
                result.setCode(10000);
                result.setData(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result updateUserName(HttpServletRequest request,String username) {
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            user.setUsername(username);
            userMapper.updateUserName(user);
            result.setMsg("修改成功");
            result.setCode(10000);
            result.setData(userMapper.getInfo(user.getId()));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result updatePassword(HttpServletRequest request,String password) {
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            user.setPassword(password);
            userMapper.updatePassword(user);
            result.setMsg("修改成功");
            result.setCode(10000);
            result.setData(userMapper.getInfo(user.getId()));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result updateAvatar_url(HttpServletRequest request, MultipartFile multipartFile) {
        Result result=new Result(-1,null,null);
        try {
            if (multipartFile.isEmpty()){
                result.setMsg("图片为空");
                return result;
            }
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);

            String originalFilename=multipartFile.getOriginalFilename();
            String ext="."+originalFilename.split("\\.")[1];
            String uuid=UUID.randomUUID().toString().replace("-","");
            String fileName=ext+uuid;

            ApplicationHome applicationHome=new ApplicationHome(this.getClass());
            String pre=applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()+
                    "\\src\\main\\resources\\static\\images";
            String avatar_url=pre+fileName;
            multipartFile.transferTo(new File(avatar_url));

            user.setAvatar_url(avatar_url);
            userMapper.updateAvatar_url(user);
            result.setMsg("修改成功");
            result.setCode(10000);
            result.setData(userMapper.getInfo(user.getId()));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Result personalArticle(HttpServletRequest request,int size,int num){
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(userMapper.personalArticle(user.getId(),size,(num-1)*size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result likeArticle(HttpServletRequest request, int size, int num) {
        Result result=new Result(-1,null,null);
        try {
            String token=request.getHeader("token");
            User user= (User) redisTemplate.opsForValue().get(token);
            result.setMsg("排序成功");
            result.setCode(10000);
            result.setData(userMapper.likeArticle(user.getId(),size,(num-1)*size).toArray(new Article[size]));
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
