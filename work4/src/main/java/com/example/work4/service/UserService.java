package com.example.work4.service;

import com.example.work4.domain.User;
import com.example.work4.tool.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Result regist(String username,String password);
    Result login(String username,String password);
    Result getInfo(HttpServletRequest request);
    Result updateUserName(HttpServletRequest request,String username);
    Result updatePassword(HttpServletRequest request,String password);
    Result updateAvatar_url(HttpServletRequest request, MultipartFile multipartFile);
    Result personalArticle(HttpServletRequest request,int size, int num);
    Result likeArticle(HttpServletRequest request,int size,int num);
}
