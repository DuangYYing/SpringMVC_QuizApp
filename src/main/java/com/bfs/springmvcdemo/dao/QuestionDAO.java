package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.Question;
import com.bfs.springmvcdemo.domain.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDAO {

    JdbcTemplate jdbcTemplate;

    QuestionRowMapper rowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuestionDAO(JdbcTemplate jdbcTemplate,QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM Question";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Question> getQuestionsByCategory(int category_id) {
        String sql = "SELECT * FROM Question WHERE category_id ="+category_id;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Question> getQuestionById(int questionId) {
        String sql = "SELECT * FROM Question WHERE question_id ="+questionId;
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst();

    }


    public int addQuestion(Question question) {
        String sql = "INSERT INTO Question (category_id,description,active) values (?, ?,?)";
        return jdbcTemplate.update(sql, question.getCategory_id(),question.getDescription(),true);
    }

    public int updateQuestion(int question_id,String description) {
        String sql = "UPDATE Question SET description = ? WHERE question_id = ?";
        return jdbcTemplate.update(sql,description, question_id);
    }

    public int disableQuestion(int questionId, String status) {
        boolean active = status.equals("Disable")?false:true;
        String sql = "UPDATE Question SET  active = ? WHERE question_id =?";
        return jdbcTemplate.update(sql, active,questionId);
    }

    public Question getLatestQuestion() {
        String sql = "SELECT * FROM Question ORDER BY question_id DESC LIMIT 1";
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst()
                .orElse(new Question(-1,"Invalid description"));
    }

    public int deleteQuestion(int questionId) {
        return jdbcTemplate.update("DELETE FROM Question WHERE question_id = ?", new Object[] { questionId });
    }
}