package com.bfs.springmvcdemo.domain;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Quiz {
    private static AtomicInteger lastUsedId = new AtomicInteger();
    private int quiz_id;
    private int user_id;
    private int category_id;
    private String name;
    private String time_start;
    private String time_end;
    private int score;





}

