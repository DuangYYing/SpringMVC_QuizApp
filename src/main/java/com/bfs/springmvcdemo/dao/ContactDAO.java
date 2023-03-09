package com.bfs.springmvcdemo.dao;

import com.bfs.springmvcdemo.domain.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ContactDAO {
    private final JdbcTemplate jdbcTemplate;

    public ContactDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        return jdbcTemplate.query("SELECT * FROM Contact", new ContactRowMapper());
    }

    public Contact getContactById(int contactId) {
        return jdbcTemplate.queryForObject("SELECT * FROM Contact WHERE id = ?", new Object[] { contactId },
                new ContactRowMapper());
    }

    public int addContact(Contact contact) {
        String sql = "INSERT INTO Contact (firstname, lastname,subject,message) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,contact.getFirstname(),contact.getLastname(),contact.getSubject(),contact.getMessage());
    }

    public int updateContact(Contact contact) {
        return jdbcTemplate.update("UPDATE contact SET firstname=?, lastname = ?, subject = ?, message = ? WHERE contact_id = ?",
                new Object[] { contact.getFirstname(), contact.getLastname(), contact.getSubject(), contact.getMessage(), contact.getContact_id() });
    }

    public int deleteContact(int contactId) {
        return jdbcTemplate.update("DELETE FROM contact WHERE contact_id = ?", new Object[] { contactId });
    }
}
