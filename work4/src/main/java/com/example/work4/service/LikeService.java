package com.example.work4.service;

import com.example.work4.domain.Like;
import com.example.work4.tool.Result;

import javax.servlet.http.HttpServletRequest;

public interface LikeService {
    Result updateLikenum(HttpServletRequest request, int k,int id);
}
