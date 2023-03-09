package com.bfs.springmvcdemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bfs.springmvcdemo.domain.User;
import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {this.userService = userService; }

    public Optional<User> validateLogin(String email, String password) {
        return userService.validateLogin(email, password);
    }


}
