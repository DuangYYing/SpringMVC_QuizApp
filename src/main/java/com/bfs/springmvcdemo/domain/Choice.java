package com.bfs.springmvcdemo.domain;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choice_id;
    private int question_id;
    private String choice_description;
    private boolean correct;

    public Choice(int question_id,String choice_description,boolean correct){
        this.question_id = question_id;
        this.choice_description = choice_description;
        this.correct = correct;
    }
}
