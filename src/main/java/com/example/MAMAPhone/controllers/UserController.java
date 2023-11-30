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
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
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


    /*@GetMapping("/changeRate")
    public String changeRate(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
        model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "change_rate";
    }*/

    @PostMapping("/chooseRate/{id}")
    public String chooseRate(@PathVariable Long id, @ModelAttribute("rate") Rate rate, Principal principal, Model model) {
        User user = rateService.getUserByPrincipal(principal);
        String answerChooseRate = userService.chooseRate(user, rateService.getRateById(id));
        //return "redirect:/rate-info";
        return "redirect:/";
    }



    final String NUM_OF_CARD = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
    final String CVCstatic = "[0-9][0-9][0-9]";
    @PostMapping("/top_up_balance")
    public String topUpBalance(/*BindingResult bindingResult,*/ Principal principal, Model model, Integer balance, String numOfCard, String CVC) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("balance", balance);
        model.addAttribute("numOfCard", numOfCard);
        model.addAttribute("CVC", CVC);
        if (!numOfCard.matches(NUM_OF_CARD)) {
            if (!CVC.matches(CVCstatic)) {
                model.addAttribute("errorCVC", "CVC должен быть введён корректно (***)");
            }
            model.addAttribute("errorNum", "Номер карты должен быть введён корректно (****-****-****-****)");
            return "topUpBalance";
        }
        if (!CVC.matches(CVCstatic)) {
            if (!numOfCard.matches(NUM_OF_CARD)) {
                model.addAttribute("errorNum", "Номер карты должен быть введён корректно (****-****-****-****)");
            }
            model.addAttribute("errorCVC", "CVC должен быть введён корректно (***)");
            return "topUpBalance";
        }
        Card card = cardService.loadCardByNumOfCard(numOfCard);
        if (card == null) {
            model.addAttribute("errorNum", "Номер карты не существует.");
            return "topUpBalance";
        }
        if (balance <= 0) {
            model.addAttribute("errorBalance", "Заполните поле баланса.");
            return "topUpBalance";
        }
        log.info("Баланс счёта телефона " + user.getPhoneNum() + " равен = " + user.getBalance());
        if ((user.getBalance() + balance) > 99000) {
            model.addAttribute("errorBalanceHigh", "Нельзя пополнить баланс мобильного счёта выше 99000 рублей.");
            return "topUpBalance";
        }

        log.info("Баланс = " + balance + "; Номер карты = " + numOfCard);
        log.info("Прохождение транзакции");

        String answerOfTransaction = transactionService.createTransaction(user, numOfCard, CVC, balance);
        if (answerOfTransaction != "") {
            model.addAttribute("errorAnswerOfTransaction", answerOfTransaction);
            return "topUpBalance";
        }
        cardService.updateBalanceCard(card.getId(), ((-1) * balance));
        String answerUserTopUpBalance = userService.topUpBalance(user, balance);
        if (answerUserTopUpBalance != "") {
            model.addAttribute("errorAnswerUserTopUpBalance", answerUserTopUpBalance);
            return "topUpBalance";
        }
        return "redirect:/";
    }


    @GetMapping("/change_password")
    public String changePassword(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "change_password";
    }


    @PostMapping("/change_password")
    public String funcChangePassword(@RequestParam String newPassword, @RequestParam String oldPassword, Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        log.info("Новый пароль: " + newPassword + "; Старый пароль: " + oldPassword);
        String answerOfPassword = userService.changePassword(rateService.getUserByPrincipal(principal), oldPassword, newPassword);
        model.addAttribute("errorAnswerOfPassword", answerOfPassword);
        return "/change_password";
    }

}
