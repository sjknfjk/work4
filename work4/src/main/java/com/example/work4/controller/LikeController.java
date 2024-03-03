package com.example.work4.controller;

import com.example.work4.tool.Result;
import com.example.work4.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class LikeController {
    @Autowired
    private LikeService likeService;
    @RequestMapping("/like/update")
    public Result updateLikenum(HttpServletRequest request, int k,int id){
        log.debug("根据k模式更新点赞数");
        return likeService.updateLikenum(request,k,id);
    }
}
