package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.Feedback;
import com.bfs.springmvcdemo.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackRowMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setFeedback_id(rs.getInt("feedback_id"));
        feedback.setUser_id(rs.getInt("user_id"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setDate(rs.getString("date"));
        feedback.setMessage(rs.getString("message"));
        return feedback;
    }
}
