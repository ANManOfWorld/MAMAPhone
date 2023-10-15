package com.example.MAMAPhone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController { //прием HTTP запросов
    @GetMapping("/")
    public String billing() {
        return "billing";
    }
}
