package com.example.work4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {
    private int id;
    private int auther_id;
    private int article_id = -1;
    private int parent_id = -1;
    private int likenum = 0;
    private int child_count = 0;
    private String content;
    private String updated_time;

    public Comment(int auther_id, int article_id, int parent_id, String content) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.auther_id = auther_id;
        this.article_id = article_id;
        this.parent_id = parent_id;
        this.content = content;
        this.updated_time = simpleDateFormat.format(date);
    }
}
