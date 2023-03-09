package com.bfs.springmvcdemo.dao;
import com.bfs.springmvcdemo.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContact_id(rs.getInt("contact_id"));
        contact.setFirstname(rs.getString("firstname"));
        contact.setLastname(rs.getString("lastname"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        return contact;
    }
}
