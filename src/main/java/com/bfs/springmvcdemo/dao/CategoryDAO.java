package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {
    JdbcTemplate jdbcTemplate;

    CategoryRowMapper rowMapper;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategoryDAO(JdbcTemplate jdbcTemplate,CategoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Category";
        List<Category> categories = jdbcTemplate.query(sql, rowMapper);
        return categories;
    }

    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE category_id = "+categoryId;
        return jdbcTemplate.query(sql,rowMapper).stream().findFirst()
                .orElse(new Category(-1,"Invalid_name","Invalid_url"));
    }

    public int addCategory(Category category) {
        String sql = "INSERT INTO Category (name, image_url) values (?, ?)";
        return jdbcTemplate.update(sql,category.getName(),category.getImage_url());
    }

    public int updateCategory(Category category) {
        return jdbcTemplate.update("UPDATE category SET name = ?, image_url = ? WHERE category_id = ?",
                new Object[] { category.getName(), category.getImage_url(), category.getCategory_id()});
    }

    public int deleteCategory(int categoryId) {
        return jdbcTemplate.update("DELETE FROM category WHERE category_id = ?", new Object[] { categoryId });
    }
}
