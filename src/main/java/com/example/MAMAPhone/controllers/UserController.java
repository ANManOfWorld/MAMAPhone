package com.example.MAMAPhone.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //связь между компонентами и выполнение действий согласно переданных запросов
@RequiredArgsConstructor //удаляет конструктор из класса
public class UserController {
    /*@GetMapping("/registration")
    public String registration() {
        return "registration";
    }*/
}
