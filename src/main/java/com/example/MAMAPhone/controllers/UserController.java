package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller //связь между компонентами и выполнение действий согласно переданных запросов
@RequiredArgsConstructor //удаляет конструктор из класса
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    final String PHONE_TEMPLATE = "\\+7\\([0-9][0-9][0-9]\\)[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]"/*"+7\\d{10}"*/;
    @PostMapping("/registration")
    public  String createUser (@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, Errors error) {
        String phone = user.getPhoneNum();

        if (bindingResult.hasFieldErrors("name")) {
            //error.rejectValue("name", "user.exist", "Имя должно быть не менее 2 символов и не более 32");
            model.addAttribute("errorName", "ФИО должно быть заполнено; Имя должно быть не менее 2 символов и не более 32");

            if (bindingResult.hasFieldErrors("email")) {
                model.addAttribute("errorEmail", "Email должен быть заполнен; Email должен быть валидным");
            }
            if ((bindingResult.hasFieldErrors("phoneNum")) || (!phone.matches(PHONE_TEMPLATE))) {
                model.addAttribute("errorNum", "Телефон должен быть введён; Телефон должен быть введён корректно (+7(***)***-**-**)");
            }
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("errorPas", "Пароль должен быть введён");
            }

            return "registration";
        }

        if (bindingResult.hasFieldErrors("email")) {
            model.addAttribute("errorEmail", "Email должен быть заполнен; Email должен быть валидным");

            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("errorName", "ФИО должно быть заполнено; Имя должно быть не менее 2 символов и не более 32");
            }
            if ((bindingResult.hasFieldErrors("phoneNum")) || (!phone.matches(PHONE_TEMPLATE))) {
                model.addAttribute("errorNum", "Телефон должен быть введён; Телефон должен быть введён корректно (+7(***)***-**-**)");
            }
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("errorPas", "Пароль должен быть введён");
            }

            return "registration";
        }


        if ((bindingResult.hasFieldErrors("phoneNum")) || (!phone.matches(PHONE_TEMPLATE))) {
            model.addAttribute("errorNum", "Телефон должен быть введён; Телефон должен быть введён корректно (+7(***)***-**-**)");

            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("errorName", "ФИО должно быть заполнено; Имя должно быть не менее 2 символов и не более 32");
            }
            if (bindingResult.hasFieldErrors("email")) {
                model.addAttribute("errorEmail", "Email должен быть заполнен; Email должен быть валидным");
            }
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("errorPas", "Пароль должен быть введён");
            }
            return "registration";
        }

        if (bindingResult.hasFieldErrors("password")) {
            model.addAttribute("errorPas", "Пароль должен быть введён");
            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("errorName", "ФИО должно быть заполнено; Имя должно быть не менее 2 символов и не более 32");
            }
            if (bindingResult.hasFieldErrors("email")) {
                model.addAttribute("errorEmail", "Email должен быть заполнен; Email должен быть валидным");
            }
            if ((bindingResult.hasFieldErrors("phoneNum")) || (!phone.matches(PHONE_TEMPLATE))) {
                model.addAttribute("errorNum", "Телефон должен быть введён; Телефон должен быть введён корректно (+7(***)***-**-**)");
            }
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            //return "redirect:/registration";
            return "registration";
        }

        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }

}
