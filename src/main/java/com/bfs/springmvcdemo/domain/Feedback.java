package com.bfs.springmvcdemo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    private int feedback_id;
    private int user_id;
    private String message;
    private int rating;
    private String date;
    public Feedback(int user_id,String message,int rating,String date){
        this.user_id = user_id;
        this.message = message;
        this.rating = rating;
        this.date = date;
    }
}
