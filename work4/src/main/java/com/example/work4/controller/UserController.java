package com.example.work4.controller;

import com.example.work4.domain.User;
import com.example.work4.tool.Result;
import com.example.work4.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public Result regist(String username,String password){
        log.debug("注册");
        return userService.regist(username, password);
    }
    @GetMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password){
        log.debug("登陆");
        return userService.login(username, password);
    }
    @GetMapping("/info")
    public Result getInfo(HttpServletRequest request){
        log.debug("获取用户信息");
        return userService.getInfo(request);
    }
    @RequestMapping("/nameChange")
    public Result updateUsername(HttpServletRequest request,String username){
        log.debug("更改用户名");
        return userService.updateUserName(request,username);
    }
    @RequestMapping("/passwordChange")
    public Result updatePassword(HttpServletRequest request,String password){
        log.debug("更改密码");
        return userService.updatePassword(request,password);
    }
    @RequestMapping("/avatarChange")
    public Result updateAvatar_url(HttpServletRequest request, MultipartFile multipartFile){
        log.debug("更改头像");
        return userService.updateAvatar_url(request,multipartFile);
    }
    @RequestMapping("/articleList")
    public Result personalArticle(HttpServletRequest request,int size,int num){
        log.debug("个人文章列表");
        return userService.personalArticle(request, size, num);
    }
    @RequestMapping("/likeList")
    public Result likeArticle(HttpServletRequest request, int size, int num) {
        log.debug("个人点赞列表");
        return userService.likeArticle(request, size, num);
    }
}
