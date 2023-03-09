package com.bfs.springmvcdemo.service;

import com.bfs.springmvcdemo.dao.ContactDAO;
import com.bfs.springmvcdemo.dao.FeedbackDAO;
import com.bfs.springmvcdemo.dao.UserDAO;
import com.bfs.springmvcdemo.domain.Contact;
import com.bfs.springmvcdemo.domain.Feedback;
import com.bfs.springmvcdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDao;
    private final FeedbackDAO feedbackDAO;

    private final ContactDAO contactDAO;

    @Autowired
    public UserService(UserDAO userDao, FeedbackDAO feedbackDAO,  ContactDAO contactDAO) {
        this.userDao = userDao;
        this.feedbackDAO = feedbackDAO;
        this.contactDAO = contactDAO;

    }

    public void createNewUser(User user) {
        userDao.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    public User getUserById(int id) {
        return userDao.getUsers().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new User(-1, "InvalidAddress","invalid firstname","invalid lastname", "invalid email","invalid password","invalid phone",false,false));

    }

    public Optional<User> validateLogin(String email, String password) {
        // check if a user with the given username and password exists
        return userDao.getUsers().stream()
                .filter(a -> a.getEmail().equals(email)
                        && a.getPassword().equals(password))
                .findAny();
    }

    public void addFeedback(Feedback feedback){
        feedbackDAO.addFeedback(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackDAO.getAllFeedback();
    }

    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    public void addContact(Contact contact){
        contactDAO.addContact(contact);
    }

    public String getDate(long currentTimeInMillis){
        Date date = new Date(currentTimeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public void suspendUser(int userId){
        userDao.suspendUser(userId);

    }
    public void activeUser(int userId){
        userDao.activeUser(userId);

    }

}
