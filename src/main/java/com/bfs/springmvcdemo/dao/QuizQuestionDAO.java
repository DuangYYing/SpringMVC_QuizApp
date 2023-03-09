package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.Choice;
import com.bfs.springmvcdemo.domain.Question;
import com.bfs.springmvcdemo.domain.Quiz;
import com.bfs.springmvcdemo.domain.QuizQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizQuestionDAO {

    JdbcTemplate jdbcTemplate;
    QuizQuestionRowMapper rowMapper;
    QuestionRowMapper questionrowMapper;

    QuizRowMapper quizRowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuizQuestionDAO(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<QuizQuestion> getAllQuizQuestions() {
        String sql = "SELECT * FROM QuizQuestion";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<QuizQuestion> getAllQuizQuestionByQuizId(int quizId) {
        String sql = "SELECT * FROM QuizQuestion WHERE quiz_id = "+quizId;
        List<QuizQuestion> quizQuestions = jdbcTemplate.query(sql, rowMapper);
        return quizQuestions;
    }

    public int addQuizQuestion(QuizQuestion quizQuestion) {
        String sql = "INSERT INTO QuizQuestion (quiz_id,question_id,user_choice_id,order_num, is_marked) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql, quizQuestion.getQuiz_id(),quizQuestion.getQuestion_id(),quizQuestion.getUser_choice_id(),quizQuestion.getOrder_num(),quizQuestion.is_marked());
    }

}


