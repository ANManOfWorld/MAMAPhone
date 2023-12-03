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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        User user = rateService.getUserByPrincipal(principal);
       // model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", user);

        Double minutesInPercent = 0.0;
        String minutes = "0%";
        Double internetOfPercent = 0.0;
        String internet = "0%";
        if (user.getCurrentRate() != null) {
            if (user.getCurrentRate().getCountOfMinutes() > 0) {
                //log.info("user.getMinutes() = " + user.getMinutes() + " ; user.getCurrentRate() = " + user.getCurrentRate().getCountOfMinutes());
                minutesInPercent = ((double) user.getMinutes() / (double) user.getCurrentRate().getCountOfMinutes()) * 100;
                minutes = minutesInPercent.toString() + "%";
            }
            if (user.getCurrentRate().getCountOfTrafficInternet() > 0) {
                internetOfPercent = (user.getInternet() / user.getCurrentRate().getCountOfTrafficInternet()) * 100;
                internet = internetOfPercent.toString() + "%";
            }
        }
        //log.info("!!!!!!!!!!!!!MINUTES = " + minutes);
        //log.info("!!!!!!!!!!!!!INTERNET = " + internet);
        model.addAttribute("minutesInPercent", minutes);
        model.addAttribute("internetOfPercent", internet);

        if (principal == null) {
            return "main_page_unauthorized";
        } else {
            //return "billing";
            return "main_page";
        }
    }


    private Integer index = 0;
    private Integer max = 0;
    private Boolean flag = false;
    List<Rate> list;
    @GetMapping("/changeRate")
    public String rateInfo(/*@PathVariable Long id,*/ Model model/*, Boolean next, Boolean pre*/) {
        list = new ArrayList<Rate>(rateService.takeAll());
        max = list.size();
        /*log.info("Вывод всех тарифов: " + list.toString());*/
        /*model.addAttribute("next", next);
        model.addAttribute("pre", pre);*/

        Rate rate = list.get(index);
        model.addAttribute("rate", rate);
        flag = true;
        return "change_rate";

        /*while (flag) {
            if ((next == false) && (pre == false)) {
                index = 0;
                flag = false;
                break;
            }
            if (next == true) {
                index++;
                rate = list.get(index);
                model.addAttribute("rate", rate);
                return "change_rate";
            }
            if (pre == true) {
                index--;
                rate = list.get(index);
                model.addAttribute("rate", rate);
                return "change_rate";
            }
        }*/

       /* Rate rate = rateService.getRateById(id);
        model.addAttribute("rate", rate);

        return "redirect:/main-page";*/
    }
    @PostMapping("/changeRate/next")
    public String rateInfoNext(/*@PathVariable Long id,*/ Model model, String next/*, String pre*/) {
        log.info("СЧЁТ!: " + index);
        model.addAttribute("next", next);

        Rate rate;
        if (flag) {
            if ((next == "")) {
                index = 0;
                flag = false;
            }
            if ((next != "")) {
                index = (index + 1 + max) % max;
                rate = list.get(index);
                model.addAttribute("rate", rate);
                return "change_rate";
            }
        }
        return "change_rate";
    }
    @PostMapping("/changeRate/pre")
    public String rateInfoPre(/*@PathVariable Long id,*/ Model model/*, String next*/, String pre) {
        log.info("СЧЁТ!: " + index);
        model.addAttribute("pre", pre);

        Rate rate;

        if (flag) {
            if ((pre == "")) {
                index = 0;
                flag = false;
            }
            if ((pre != "")){
                index = (index - 1 + max) % max;
                rate = list.get(index);
                model.addAttribute("rate", rate);
                return "change_rate";
            }
        }
        return "change_rate";
    }



    /*@GetMapping("/rate/{id}")
    public String rateInfo(@PathVariable Long id, Model model) {
        Rate rate = rateService.getRateById(id);
        model.addAttribute("rate", rate);
        model.addAttribute("images", rate.getImages());  // Фотография функция по передаче её в "Подробнее"
        return "rate-info";
    }
    */

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
    public String func_change_personal_data(Model model, Principal principal, String name, String lastName, String fatherName, String birth, String email) throws ParseException {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("fatherName", fatherName);
        model.addAttribute("birth", birth);
        model.addAttribute("email", email);
        log.info("(!!!) ДАТА = " + birth);
        userService.changeBirth(user, birth);


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



    @GetMapping("/decrement")
    public String getTimeManager(Model model) {
        model.addAttribute("users", userService.list());
        return "decrement";
    }

    @PostMapping("/decrement/create/{id}")
    public String changeTimeManager(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model, Double internet, Integer minutes) {
        model.addAttribute("internet", internet);
        model.addAttribute("minutes", minutes);
        if (internet >= 0) {
            if (user.getInternet() >= internet) {
                userService.changeInternet(id, user.getInternet());
            }
        }

        if (minutes >= 0) {
            if (user.getMinutes() >= minutes) {
                userService.changeMinutes(id, user.getMinutes());
            }
        }
        return "redirect:/decrement";
    }


}
