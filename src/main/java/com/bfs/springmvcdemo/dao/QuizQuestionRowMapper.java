package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.QuizQuestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizQuestionRowMapper implements RowMapper<QuizQuestion> {
    @Override
    public QuizQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQq_id(rs.getInt("qq_id"));
        quizQuestion.setQuiz_id(rs.getInt("quiz_id"));
        quizQuestion.setQuestion_id(rs.getInt("question_id"));
        quizQuestion.setUser_choice_id(rs.getInt("user_choice_id"));
        quizQuestion.setOrder_num(rs.getInt("order_num"));
        quizQuestion.set_marked(rs.getBoolean("is_marked"));
        return quizQuestion;
    }
}
