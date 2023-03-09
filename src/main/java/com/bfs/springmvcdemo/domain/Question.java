package com.bfs.springmvcdemo.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int question_id;
    private int category_id;
    private String description;
    private boolean active;
    private List<Choice> choices;

    public Question(int category_id,String description){
        this.category_id = category_id;
        this.description = description;
    }

}
