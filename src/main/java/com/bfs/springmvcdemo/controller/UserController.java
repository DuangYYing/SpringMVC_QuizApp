package com.bfs.springmvcdemo.controller;

import com.bfs.springmvcdemo.domain.Contact;
import com.bfs.springmvcdemo.domain.Feedback;
import com.bfs.springmvcdemo.domain.User;
import com.bfs.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user";
    }
    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String createNewUser(
                                @RequestParam String address,
                                @RequestParam String firstname,
                                @RequestParam String lastname,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String phone,
                                @RequestParam boolean isAdmin,
                                Model model) {
        userService.createNewUser(new User(address,firstname,lastname,email,password,phone,isAdmin));
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/feedback")
    public String getFeedback(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||((User)session.getAttribute("user")).isAdmin() == true) {
            return "redirect:/login";
        }
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(
                @RequestParam(name = "rating") Integer rating,
                @RequestParam(name = "message") String message,
                HttpSession session){

        int user_Id = ((User) session.getAttribute("user")).getId();
        long time = System.currentTimeMillis();
        String date = userService.getDate(time);
        Feedback feedback = new Feedback(user_Id,message,rating,date);
        userService.addFeedback(feedback);
        return "index";

    }

    @GetMapping("/contactus")
    public String getContact(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||((User)session.getAttribute("user")).isAdmin() == true) {
            return "redirect:/login";
        }
        return "contactus";
    }

    @PostMapping("/contactus")
    public String submitContact(
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "message") String message,
            HttpSession session){
        User user = (User) session.getAttribute("user");
        Contact contact = new Contact(user.getFirstname(),user.getLastname(),subject,message);
        userService.addContact(contact);
        return "index";

    }

    @GetMapping("/viewfeedbacks")
    public String getAllFeedbacks(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null|| ((User)session.getAttribute("user")).isAdmin() == false) {
            return "redirect:/login";
        }
        List<Feedback> feedbacks = userService.getAllFeedbacks();
        List<Contact> contacts = userService.getAllContacts();
        model.addAttribute("feedbacks",feedbacks);
        model.addAttribute("contacts",contacts);

        return "viewfeedbacks";
    }

    @GetMapping("/userprofile")
    public String getAllUsers(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null|| ((User)session.getAttribute("user")).isAdmin() == false) {
            return "redirect:/login";
        }
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers",allUsers);
        return "userprofile";
    }

    @PostMapping ("suspendUserProfile")
    public String suspendUserProfile(
            @RequestParam(name = "userId") int userId,
            HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null || ((User)session.getAttribute("user")).isAdmin() == false) {
            return "redirect:/login";
        }
        userService.suspendUser(userId);
        return "redirect:/userprofile";
    }

    @PostMapping("activateUserProfile")
    public String activateUserProfile(
            @RequestParam(name = "userId") int userId,
            HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null || ((User)session.getAttribute("user")).isAdmin() == false) {
            return "redirect:/login";
        }
        userService.activeUser(userId);
        return "redirect:/userprofile";
    }





    // Use ResponseBody if you want to return a JSON object instead of a view

    @GetMapping("/api/users")
    @ResponseBody //
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/user/{user_id}")
    @ResponseBody
    public User getUserById(@PathVariable int user_id) {
        return userService.getUserById(user_id);
    }


    @PostMapping("/api/user")
    @ResponseBody
    public String createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        return "User inserted";
    }
}
