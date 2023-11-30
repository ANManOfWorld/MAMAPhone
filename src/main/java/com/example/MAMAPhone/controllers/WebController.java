package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.services.RateService;
import com.example.MAMAPhone.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

@Slf4j
@Controller //связь между компонентами и выполнение (обработка запросов) действий согласно переданных запросов
public class WebController { //прием HTTP запросов
    private final RateService rateService;
    private final UserService userService;


    public WebController(RateService rateService, UserService userService) {
        this.rateService = rateService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String billing(/*@RequestParam(name = "name", required = false) String name,*/ Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       // model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        if (principal == null) {
            return "main_page_unauthorized";
        } else {
            //return "billing";
            return "main_page";
        }
    }

    @GetMapping("/rate/{id}")
    public String rateInfo(@PathVariable Long id, Model model) {
        Rate rate = rateService.getRateById(id);
        model.addAttribute("rate", rate);
        model.addAttribute("images", rate.getImages());  // Фотография функция по передаче её в "Подробнее"
        return "rate-info";
    }

    @GetMapping("/rate/admin/{id}")
    public String rateInfoAdmin(@PathVariable Long id, Model model) {
        Rate rate = rateService.getRateById(id);
        model.addAttribute("rate", rate);
        model.addAttribute("images", rate.getImages());  // Фотография функция по передаче её в "Подробнее"
        return "rate-info-admin";
    }

// ---------------------------------- ДО картинок
   /* @PostMapping("/rate/create")
    public String createRate (Rate rate) {
        rateService.saveRate(rate);
        return "redirect:/"; //обновление страницы
    }
*/
    // ---------------------------------- Картинки
    @PostMapping("/rate/create")
    public String createRate (@RequestParam("file1")MultipartFile file1, Rate rate, Principal principal) throws IOException {
        rateService.saveRate(rate, file1, principal);
        return "redirect:/"; //обновление страницы
    }
    // ---------------------------------- Картинки

    @PostMapping("/rate/delete/{id}")
    public String deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return "redirect:/"; //обновление страницы
    }



    @GetMapping("/billing")
    public String billing(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
        model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));

        return "billing";
    }


/* ВКЛАДКА НАСТРОЙКИ */
    @GetMapping("/settings")
    public String settings(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "settings";
    }

    @GetMapping("/personalData")
    public String personalData(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "personal_data";
    }

    @GetMapping("/change_personal_data")
    public String change_personal_data(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "change_personal_data";
    }

    @PostMapping("/change_personal_data/change")
    public String func_change_personal_data(Model model, Principal principal, String name, String lastName, String fatherName, Date date, String email) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("fatherName", fatherName);
        model.addAttribute("date", date);
        model.addAttribute("email", email);


        /*log.info("Проверка почты на уникальность и не повторяемость.");
        if (!user.getEmail().equals(email) ) {
            log.info("Первая проверка: " + user.getEmail().equals(email));
            if (userService.loadUserByUsername(email) != null) {
                log.info("Вторая проверка: " + userService.loadUserByUsername(email).toString());
                model.addAttribute("errorMessage", "Email: " + user.getEmail() + " уже используется. Выберите другую эл. почту");
                return "change_personal_data";
            }
        }*/
       /* if ((userService.loadUserByUsername(email) != null) && (user.getEmail() == email)) {
            model.addAttribute("errorMessage", "Email: " + user.getEmail() + " уже используется. Выберите другую эл. почту");
            return "change_personal_data";
        }*/

        log.info("Передача данных в метод для дальнейшего изменения параметров пользователя.");
        String errorValidName = userService.changeName(user, name);
        String errorValidLastName =  userService.changeLastName(user, lastName);
        String errorValidFatherName =  userService.changeFartherName(user, fatherName);

        String errorValidEmail = userService.changeEmail(user, email);

        log.info("errorValidName: " + errorValidName);
        log.info("errorValidLastName: " + errorValidLastName);
        log.info("errorValidFatherName: " + errorValidFatherName);
        log.info("errorValidEmail: " + errorValidEmail);

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
            if (errorValidEmail.equals("good")) {
                String good = "good";
                model.addAttribute("good", good);
            } else {
                model.addAttribute("errorValidEmail", errorValidEmail);
            }
        }

        if ((!errorValidName.equals("")) || (!errorValidLastName.equals("")) || (!errorValidFatherName.equals("")) || (!errorValidEmail.equals(""))) {
            return "change_personal_data";
        }

        //userService.changePersonalData(user, name, lastName, fatherName, date);




        //return "redirect:/personal_data";
        return "personal_data";
    }



    @GetMapping("/security")
    public String security(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "security";
    }

    @GetMapping("/change_security")
    public String change_security(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "change_security";
    }

    final String NUM_OF_CARD = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
    final String CVCstatic = "[0-9][0-9][0-9]";
    @PostMapping("/security/change")
    public String func_change_security(Model model, Principal principal, String CVC, String numOfCard) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("CVC", CVC);
        model.addAttribute("numOfCard", numOfCard);

        if (!numOfCard.matches(NUM_OF_CARD)) {
            if (!CVC.matches(CVCstatic)) {
                model.addAttribute("errorCVC", "CVC должен быть введён корректно (***)");
            }
            model.addAttribute("errorNum", "Номер карты должен быть введён корректно (****-****-****-****)");
            return "change_security";
        }
        if (!CVC.matches(CVCstatic)) {
            if (!numOfCard.matches(NUM_OF_CARD)) {
                model.addAttribute("errorNum", "Номер карты должен быть введён корректно (****-****-****-****)");
            }
            model.addAttribute("errorCVC", "CVC должен быть введён корректно (***)");
            return "change_security";
        }
        userService.changeCVC(user, CVC);
        userService.changeNumOfCard(user, numOfCard);
        return "redirect:/security";
    }

    @PostMapping("/security/delete")
    public String delete_change_security(Model model, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        userService.changeCVC(user, "XXX");
        userService.changeNumOfCard(user, "XXXX-XXXX-XXXX-XXXX");
        return "redirect:/security";
    }
    /* ВКЛАДКА НАСТРОЙКИ */




    @GetMapping("/top_up_balance")
    public String appSettings(Model model, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "topUpBalance";
    }

}
