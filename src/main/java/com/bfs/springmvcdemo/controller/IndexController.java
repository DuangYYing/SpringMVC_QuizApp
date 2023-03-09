package com.bfs.springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    // Same logic as about
    @GetMapping(value = "/index") //localhost:8080/about2
    public String getIndex() {
        return "index";
    }
}
