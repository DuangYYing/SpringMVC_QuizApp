package com.bfs.springmvcdemo.domain;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestion {
    private int qq_id;
    private int quiz_id;
    private int question_id;
    private int user_choice_id;
    private int order_num;
    private boolean is_marked;

    public QuizQuestion(int quiz_id, int question_id, int user_choice_id, int order_num, boolean is_marked){
        this.quiz_id = quiz_id;
        this.question_id = question_id;
        this.user_choice_id = user_choice_id;
        this.order_num = order_num;
        this.is_marked = is_marked;
    }

}
