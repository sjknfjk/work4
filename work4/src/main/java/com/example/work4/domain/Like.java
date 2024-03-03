package com.example.work4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Like {
    private int id;
    private int user_id;
    private int article_id=-1;
    private int comment_id=-1;

    public Like(int user_id, int article_id, int comment_id) {
        this.user_id = user_id;
        this.article_id = article_id;
        this.comment_id = comment_id;
    }
}
