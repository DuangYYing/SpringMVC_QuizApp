package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.Question;
import com.bfs.springmvcdemo.domain.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setQuestion_id(rs.getInt("question_id"));
        question.setCategory_id(rs.getInt("category_id"));
        question.setDescription(rs.getString("description"));
        question.setActive(rs.getBoolean("active"));
        return question;
    }
}
