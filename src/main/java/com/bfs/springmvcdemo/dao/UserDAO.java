package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {
    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper,NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    public void addUser(User user) {
        String sql = "INSERT INTO User (address,firstname, lastname, email,password,phone,active,admin) VALUES (?, ?, ?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getAddress(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(),user.getPhone(),user.isActive(),user.isAdmin());
    }

    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM User WHERE user_id = "+id;
        return jdbcTemplate.query(sql,rowMapper).stream().findFirst();
    }

    public List<User> getUsers() {
        String sql = "SELECT * FROM User";
        List<User> users = jdbcTemplate.query(sql, rowMapper);
        return users;
    }

    public int suspendUser(int userId){
        String sql = "UPDATE User SET  active = ? WHERE user_id =?";
        return jdbcTemplate.update(sql, false,userId);
    }

    public int activeUser(int userId){
        String sql = "UPDATE User SET  active = ? WHERE user_id =?";
        return jdbcTemplate.update(sql, true,userId);
    }
}
