package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDAO {

    JdbcTemplate jdbcTemplate;
    FeedbackRowMapper rowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FeedbackDAO(JdbcTemplate jdbcTemplate,FeedbackRowMapper rowMapper,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    public List<Feedback> getAllFeedback() {
        String sql = "SELECT * FROM Feedback";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Feedback getFeedbackById(int feedbackId) {
        String sql ="SELECT * FROM Feedback WHERE id = "+feedbackId;
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    public int addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (user_id, message,rating,date) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,feedback.getUser_id(),feedback.getMessage(),feedback.getRating(), feedback.getDate() );
    }

    public int updateFeedback(Feedback feedback) {
        return jdbcTemplate.update("UPDATE feedback SET user_id = ?, message = ?, rating = ?,date = ? WHERE feedback_id = ?",
                new Object[] { feedback.getUser_id(), feedback.getMessage(), feedback.getRating(),feedback.getDate(), feedback.getFeedback_id() });
    }

    public int deleteFeedback(int feedbackId) {
        return jdbcTemplate.update("DELETE FROM feedback WHERE id = ?", new Object[] { feedbackId });
    }
}