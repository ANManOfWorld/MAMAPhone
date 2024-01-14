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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String login(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("error", "Неправильные имя/пароль.");
        return "login";
    }

    final String PHONE_TEMPLATE = "\\+7 \\([0-9][0-9][0-9]\\)[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]"/*"+7\\d{10}"*/;
    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, String name, String lastName, String fatherName, String email/*, String phone*/, String password) {
        String phone = user.getPhoneNum();
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("fatherName", fatherName);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("password", password);

        String errorValidName = userService.regName(user, name);
        String errorValidLastName =  userService.regLastName(user, lastName);
        String errorValidFatherName =  userService.regFartherName(user, fatherName);
        String errorValidEmail = userService.regEmail(user, email);
        String errorValidPhone = userService.regPhone(user, phone);
        String errorValidPassword = userService.regPassword(user, password);

        log.info("errorValidName: " + errorValidName);
        log.info("errorValidLastName: " + errorValidLastName);
        log.info("errorValidFatherName: " + errorValidFatherName);
        log.info("errorValidEmail: " + errorValidEmail);
        log.info("errorValidPhone: " + errorValidPhone);
        log.info("errorValidPassword: " + errorValidPassword);

        if (!errorValidName.equals("")) {
            model.addAttribute("errorValidName", errorValidName);
        }

        if (!errorValidLastName.equals("")) {
            model.addAttribute("errorValidLastName", errorValidLastName);
        }

        if (!errorValidFatherName.equals("")) {
            model.addAttribute("errorValidFatherName", errorValidFatherName);
        }

        if (!errorValidEmail.equals("")) {
            model.addAttribute("errorValidEmail", errorValidEmail);
        }

        if (!errorValidPhone.equals("")) {
            model.addAttribute("errorValidPhone", errorValidPhone);
        }

        if (!errorValidPassword.equals("")) {
            model.addAttribute("errorValidPassword", errorValidPassword);
        }

        if ((!errorValidName.equals("")) || (!errorValidLastName.equals("")) || (!errorValidFatherName.equals("")) || (!errorValidEmail.equals("")) || (!errorValidPhone.equals("")) || (!errorValidPassword.equals(""))) {
            return "registration";
        }

        if (!userService.createUser(user)) {
            model.addAttribute("canNotReg", "Не удалось зарегистрироваться");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/callsFinance")
    public String showCallsAndFinance(Model model, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "calls_finance";
    }


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
        return "change_password";
    }

}
