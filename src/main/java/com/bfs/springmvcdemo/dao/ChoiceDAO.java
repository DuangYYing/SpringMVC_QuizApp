package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.Choice;
import com.bfs.springmvcdemo.domain.Question;
import com.bfs.springmvcdemo.domain.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChoiceDAO {

    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ChoiceDAO(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Choice> getAllChoicesForQuestion(int questionId) {
        String sql = "SELECT * FROM Choice WHERE question_id = "+questionId;
        List<Choice> choices = jdbcTemplate.query(sql, rowMapper);
        return choices;
    }

    public Optional<Choice> getChoiceById(int choiceId) {
        String sql ="SELECT * FROM Choice WHERE choice_id = "+ choiceId;
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst();
    }



    public int addChoice(Choice choice) {
        String sql = "INSERT INTO Choice(question_id, choice_description, correct) values(?,?,?)";
        return jdbcTemplate.update(sql, choice.getQuestion_id(), choice.getChoice_description(), choice.isCorrect());
    }

    public int updateChoice(Choice choice) {
        String sql = "UPDATE Choice SET question_id=?, choice_description=?, correct=? WHERE choice_id=?";
        return jdbcTemplate.update(sql, choice.getQuestion_id(), choice.getChoice_description(), choice.isCorrect(),choice.getChoice_id());
    }

    public int deleteChoice(int choiceId) {
        return jdbcTemplate.update("DELETE FROM choice WHERE choice_id = ?", new Object[] { choiceId });
    }
}
