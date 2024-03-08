package com.example.work4.mapper;

import com.example.work4.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    void regist(String username,String password);
    User login(String username,String password);
    User getInfo(int id);
    User getInfoByName(String username);
    void updateUserName(User user);
    void updatePassword(User user);
    void updateAvatar_url(User user);
    List personalArticle(int auther_id, int size, int num);
    List likeArticle(int user_id,int size,int num);
}
