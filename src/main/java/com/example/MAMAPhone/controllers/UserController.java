package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Card;
import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.services.CardService;
import com.example.MAMAPhone.services.RateService;
import com.example.MAMAPhone.services.TransactionService;
import com.example.MAMAPhone.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller //связь между компонентами и выполнение действий согласно переданных запросов
@RequiredArgsConstructor //удаляет конструктор из класса
@Slf4j
public class UserController {
    private final UserService userService;
    private final RateService rateService;
    private final TransactionService transactionService;
    private final CardService cardService;

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
            //error.rejectValue("name", "user.exist", "Имя должно быть не менее 2 символов и не более 12");
            model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");

            if (bindingResult.hasFieldErrors("lastName")) {
                model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("fatherName")) {
                model.addAttribute("errorFatherName", "Отчество должно быть не более 12");
            }

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

        if (bindingResult.hasFieldErrors("lastName")) {
            model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");

            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("fatherName")) {
                model.addAttribute("errorFatherName", "Отчество должно быть не более 12");
            }

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

        if (bindingResult.hasFieldErrors("fatherName")) {
            model.addAttribute("errorFatherName", "Отчество должно быть не более 12");

            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("lastName")) {
                model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");
            }

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
                model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("lastName")) {
                model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("fatherName")) {
                model.addAttribute("errorFatherName", "Отчество должно быть не более 12");
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
                model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("lastName")) {
                model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("fatherName")) {
                model.addAttribute("errorFatherName", "Отчество должно быть не более 12");
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
                model.addAttribute("errorName", "Имя должно быть заполнено; Имя должно быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("lastName")) {
                model.addAttribute("errorLastName", "Фамилия должна быть заполнена; Фамилия должна быть не менее 2 символов и не более 12");
            }

            if (bindingResult.hasFieldErrors("fatherName")) {
                model.addAttribute("errorFatherName", "Отчество должно быть не более 12");
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

    @GetMapping("/callsFinance")
    public String showCallsAndFinance() {
        return "calls_finance";
    }


    @GetMapping("/changeRate")
    public String changeRate(@RequestParam(name = "name", required = false) String name,  Model model, Principal principal) {
        model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "change_rate";
    }

    @PostMapping("/chooseRate/{id}")
    public String chooseRate(@PathVariable Long id, @ModelAttribute("rate") Rate rate, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        userService.chooseRate(user, rateService.getRateById(id));
        //return "redirect:/rate-info";
        return "redirect:/";
    }


    final String NUM_OF_CARD = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
    final String CVC = "[0-9][0-9][0-9]";
    @PostMapping("/top_up_balance")
    public String topUpBalance(Integer balance, Principal principal, Model model, String numOfCard) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("balance", balance);
        model.addAttribute("numOfCard", numOfCard);
        Card card = cardService.loadCardByNumOfCard(numOfCard);
        log.info("Баланс = " + balance + "; Номер карты = " + numOfCard);
        if (balance < 99000) {
            log.info("Прохождение транзакции");
            transactionService.createTransaction(user, card, balance);
            cardService.updateBalanceCard(card.getId(), ((-1)*balance));
            userService.topUpBalance(user, balance);
        }
        return "redirect:/";
    }

}
