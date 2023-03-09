package com.bfs.springmvcdemo.dao;


import com.bfs.springmvcdemo.domain.Quiz;
import com.bfs.springmvcdemo.domain.QuizResult;
import com.bfs.springmvcdemo.domain.QuizQuestion;
import com.bfs.springmvcdemo.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class QuizDAO {

    JdbcTemplate jdbcTemplate;

    QuizRowMapper rowMapper;

    CategoryRowMapper categoryRowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuizDAO(JdbcTemplate jdbcTemplate,QuizRowMapper rowMapper,CategoryRowMapper categoryRowMapper,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.categoryRowMapper = categoryRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Quiz> getAllQuizs() {
        String sql = "SELECT * FROM Quiz";
        List<Quiz> quizs = jdbcTemplate.query(sql, rowMapper);
        return quizs;
    }

    public List<Quiz> getAllQuizsByUserId(int user_id) {
        String sql = "SELECT * FROM Quiz WHERE user_id = "+user_id;
        List<Quiz> quizs = jdbcTemplate.query(sql, rowMapper);
        return quizs;
    }

    public List<Quiz> getQuizById(int quizId) {
        String sql = "SELECT * FROM Quiz WHERE quiz_id = "+quizId;
        List<Quiz> quizs = jdbcTemplate.query(sql, rowMapper);
        return quizs;
    }

    public List<Quiz> getCategoryID(int categoryId) {
        String sql = "SELECT * FROM Quiz WHERE category_id = "+categoryId;
        List<Quiz> quizs = jdbcTemplate.query(sql, rowMapper);
        return quizs;
    }

    public Quiz getLatestQuiz() {
        String sql = "SELECT * FROM Quiz ORDER BY quiz_id DESC LIMIT 1";
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst()
                .orElse(new Quiz(-1,-1,-1,"Invalid name","Invalid time start","Invalid time end",-1));

    }

    public int addQuiz(Quiz quiz) {
        String sql = "INSERT INTO Quiz (user_id,category_id,name, time_start, time_end,score) values (?, ?,?,?,?,?)";
        return jdbcTemplate.update(sql,quiz.getUser_id(),quiz.getCategory_id(),quiz.getName(),quiz.getTime_start(),quiz.getTime_end(),quiz.getScore());
    }






//    public int updateQuiz(Quiz quiz) {
//        return jdbcTemplate.update("UPDATE Quiz SET quiz_id = ?, user_id =?," +
//                        "name = ?, category = ? WHERE id = ?",
//                new Object[] { quiz.getName(), quiz.getCategory_id(), quiz.getQuiz_id()});
//    }
//
//    public int deleteQuiz(int quizId) {
//        return jdbcTemplate.update("DELETE FROM Quiz WHERE id = ?", new Object[] { quizId });
//    }
}