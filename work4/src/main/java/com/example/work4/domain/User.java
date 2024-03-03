package com.example.work4.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phonenum;
    private String email;
    private String avatar_url;
    private String intro;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
