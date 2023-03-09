package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategory_id(rs.getInt("category_id"));
        category.setName(rs.getString("name"));
        category.setImage_url(rs.getString("image_url"));
        return category;
    }
}
