package com.bfs.springmvcdemo.domain;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class QuizResult {

    private int quiz_id;
    private String takenDate;

    private String Category;
    private String userFullName;
    private  int numOfQuestions;
    private int score;





}

