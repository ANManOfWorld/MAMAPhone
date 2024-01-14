package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.services.DefaultEmailService;
import com.example.MAMAPhone.services.RateService;
import com.example.MAMAPhone.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (user.getCurrentRate() != null || ((user.getCurrentRate() == null) && (user.getCalendar() != null))) {
            //if (user.getCurrentRate().getCountOfMinutes() > 0) {
            if (user.getSaveMinutes() > 0) {
                //log.info("user.getMinutes() = " + user.getMinutes() + " ; user.getCurrentRate() = " + user.getCurrentRate().getCountOfMinutes());
                minutesInPercent = ((double) user.getMinutes() / (double) user.getSaveMinutes()/*user.getCurrentRate().getCountOfMinutes()*/) * 100;
                minutes = minutesInPercent.toString() + "%";
            }
            //if (user.getCurrentRate().getCountOfTrafficInternet() > 0) {
            if (user.getSaveTraffic() > 0) {
                internetOfPercent = (user.getInternet() / user.getSaveTraffic()/*user.getCurrentRate().getCountOfTrafficInternet()*/) * 100;
                internet = internetOfPercent.toString() + "%";
            }
        }
        model.addAttribute("minutesInPercent", minutes);
        model.addAttribute("internetOfPercent", internet);

        if (principal == null) {
            return "main_page_unauthorized";
        } else {
            if (user.getChangeInformationRateFlag()) {
                model.addAttribute("changeInformationRateFlag", "Условия тарифа были изменены. Вы были отключены от тарифа. Чтобы пользоваться услугами, выберите новый тариф.");
            }

            boolean WarningOfResources = false;
            boolean ConditionOfResources = false;
            if (user.getWarningOfResources()) {
                model.addAttribute("WarningOfResources", true);
                WarningOfResources = true;
            } else {
                model.addAttribute("WarningOfResources", false);
                WarningOfResources = false;
            }
            if (user.getConditionOfResources()) {
                model.addAttribute("ConditionOfResources", true);
                ConditionOfResources = true;
            } else {
                model.addAttribute("ConditionOfResources", false);
                ConditionOfResources = false;
            }
            //log.info("Уведомление = " + WarningOfResources + ", в то время как истинное = " + user.getWarningOfResources());
            //log.info("Весь трафик израсходован = " + ConditionOfResources + ", в то время как истинное = " + user.getConditionOfResources());
            boolean WarningOfPayment = false;
            boolean ConditionOfPayment = false;
            if (user.getWarningOfPayment()) {
                model.addAttribute("WarningOfPayment", true);
                WarningOfPayment = true;
            } else {
                model.addAttribute("WarningOfPayment", false);
                WarningOfPayment = false;
            }
            if (user.getConditionOfPayment()) {
                model.addAttribute("ConditionOfPayment", true);
                ConditionOfPayment = true;
            } else {
                model.addAttribute("ConditionOfPayment", false);
                ConditionOfPayment = false;
            }


            return "main_page";
        }
    }


    private Integer index = 0;
    private Integer max = 0;
    private Boolean flag = false;
    List<Rate> list;

    @GetMapping("/changeRate")
    public String rateInfo(/*@PathVariable Long id,*/ Model model, Principal principal) {
        index = 0;
        list = new ArrayList<Rate>(rateService.takeAll());
        User user = rateService.getUserByPrincipal(principal);
        if (user.isModerator()) {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate_moder";
            }

            max = list.size();
            log.info("Размер массива с тарифами = " + max);
            log.info("Размер INDEX'a = " + index);

            Rate rate = list.get(index);
            model.addAttribute("rate", rate);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            flag = true;

            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_moder_connect";
            } else {
                return "change_rate_moder";
            }

            //return "change_rate_moder";
        } else {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate";
            }

            max = list.size();
            log.info("Размер массива с тарифами = " + max);
            log.info("Размер INDEX'a = " + index);

            Rate rate = list.get(index);
            model.addAttribute("rate", rate);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            flag = true;

            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_connect";
            } else {
                return "change_rate";
            }
        }
    }

    @PostMapping("/changeRate/next")
    public String rateInfoNext(/*@PathVariable Long id,*/ Model model, String next, Principal principal) {
        list = new ArrayList<Rate>(rateService.takeAll());
        User user = rateService.getUserByPrincipal(principal);
        if (user.isModerator()) {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate_moder";
            }

            max = list.size();
            log.info("СЧЁТ!: " + index);
            model.addAttribute("next", next);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            Rate rate;
            if (flag) {
                if ((next == "")) {
                    index = 0;
                    flag = false;
                }
                if ((next != "")) {
                    index = (index + 1 + max) % max;
                    rate = list.get(index);
                    /*log.info("ЮЗЕРЫ ТЕКУЩЕГО ТАРИФА ===  " + rate);*/
                    model.addAttribute("rate", rate);
                    if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                        return "change_rate_moder_connect";
                    } else {
                        return "change_rate_moder";
                    }

                    //return "change_rate_moder";
                }
            }
            rate = list.get(index);
            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_moder_connect";
            } else {
                return "change_rate_moder";
            }

            //return "change_rate_moder";
        } else {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate";
            }

            max = list.size();
            log.info("СЧЁТ!: " + index);
            model.addAttribute("next", next);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            Rate rate;
            if (flag) {
                if ((next == "")) {
                    index = 0;
                    flag = false;
                }
                if ((next != "")) {
                    index = (index + 1 + max) % max;
                    rate = list.get(index);
                    /*log.info("ЮЗЕРЫ ТЕКУЩЕГО ТАРИФА ===  " + rate);*/
                    model.addAttribute("rate", rate);
                    if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                        return "change_rate_connect";
                    } else {
                        return "change_rate";
                    }
                    //return "change_rate";
                }
            }
            rate = list.get(index);
            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_connect";
            } else {
                return "change_rate";
            }
            //return "change_rate";
        }

    }

    @PostMapping("/changeRate/pre")
    public String rateInfoPre(/*@PathVariable Long id,*/ Model model/*, String next*/, String pre, Principal principal) {
        list = new ArrayList<Rate>(rateService.takeAll());
        User user = rateService.getUserByPrincipal(principal);
        if (user.isModerator()) {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate_moder";
            }
            max = list.size();
            log.info("СЧЁТ!: " + index);
            model.addAttribute("pre", pre);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            Rate rate;

            if (flag) {
                if ((pre == "")) {
                    index = 0;
                    flag = false;
                }
                if ((pre != "")) {
                    index = (index - 1 + max) % max;
                    rate = list.get(index);
                    model.addAttribute("rate", rate);
                    rate = list.get(index);
                    if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                        return "change_rate_moder_connect";
                    } else {
                        return "change_rate_moder";
                    }
                    //return "change_rate_moder";
                }
            }
            rate = list.get(index);
            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_moder_connect";
            } else {
                return "change_rate_moder";
            }
            //return "change_rate_moder";
        } else {
            if (list.size() < 2) {
                model.addAttribute("errorPointers", true);
            } else {
                model.addAttribute("errorPointers", false);
            }
            if (list.size() == 0) {
                model.addAttribute("errorList", false);
                model.addAttribute("user", rateService.getUserByPrincipal(principal));
                return "change_rate";
            }
            max = list.size();
            log.info("СЧЁТ!: " + index);
            model.addAttribute("pre", pre);
            model.addAttribute("user", rateService.getUserByPrincipal(principal));
            model.addAttribute("errorList", true);
            Rate rate;

            if (flag) {
                if ((pre == "")) {
                    index = 0;
                    flag = false;
                }
                if ((pre != "")) {
                    index = (index - 1 + max) % max;
                    rate = list.get(index);
                    model.addAttribute("rate", rate);
                    if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                        return "change_rate_connect";
                    } else {
                        return "change_rate";
                    }
                    //return "change_rate";
                }
            }
            rate = list.get(index);
            if ((user.getCurrentRate() != null) && (user.getCurrentRate().getId() == rate.getId())) {
                return "change_rate_connect";
            } else {
                return "change_rate";
            }
            //return "change_rate";
        }
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
    public String createRate(/*@RequestParam("file1")MultipartFile file1, */Model model, Rate rate, Principal principal) throws IOException {
        String errorRate = rateService.saveRate(rate, /*file1, */principal);
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        model.addAttribute("rates", rateService.takeAll());
        log.info("Ошибка создания тарифа = " + errorRate);
        if (!errorRate.equals("")) {
            model.addAttribute("errorRate", errorRate);
            return "/billing";
        }
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
        model.addAttribute("rates", rateService.takeAll()/* rateService.listRates(name)*/);
        model.addAttribute("user", rateService.getUserByPrincipal(principal));

        return "billing";
    }


    @GetMapping("/billing_change/{id}")
    public String billing_change_delete(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        //model.addAttribute("rates",rateService.takeAll()/* rateService.listRates(name)*/);
        model.addAttribute("rate", rateService.getRateById(id));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));

        return "billing_change";
    }

    @PostMapping("/billing_change/change/{id}")
    public String billing_change(@PathVariable(name = "id") Long id, Model model, Principal principal, Rate rate) throws IOException {
        String errorRate = rateService.changeRate(rateService.getRateById(id), rate/*rate*/);
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        model.addAttribute("rate", rateService.getRateById(id));

        if (!errorRate.equals("")) {
            log.info("Ошибка изменения тарифа = " + errorRate);
            model.addAttribute("Change", errorRate);
            return "billing_change";
        }
        if (rateService.getUserByPrincipal(principal).getChangeInformationRateFlag()) {
            //log.info("ФЛАГ ДООЖЕН ВЫВЕСТИСЬ ПОСЛЕ ИЗМЕНЕНИЯ!!!");
            model.addAttribute("changeInformationRateFlag", "Условия тарифа были изменены. Вы были отключены от тарифа.\nЧтобы пользоваться услугами, выберите новый тариф.");
        }
        //model.addAttribute("errorChangeRate", errorRate);
        return "redirect:/";
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

        log.info("Передача данных в метод для дальнейшего изменения параметров пользователя.");
        String errorValidName = userService.changeName(user, name);
        String errorValidLastName = userService.changeLastName(user, lastName);
        String errorValidFatherName = userService.changeFartherName(user, fatherName);

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
    public String changeTimeManager(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model, Double internet, Integer minutes, Principal principal) {
        model.addAttribute("internet", internet);
        model.addAttribute("minutes", minutes);
        //if ((internet == null) || (minutes == null)) {
        if ((internet >= 0) && (internet != null)) {
            if (user.getInternet() >= internet) {
                userService.changeInternet(id, user.getInternet());
            }
        }

        if ((minutes >= 0) && (minutes != null)) {
            if (user.getMinutes() >= minutes) {
                userService.changeMinutes(id, user.getMinutes());
            }
        }
        //}

        User user2 = rateService.getUserByPrincipal(principal);
        log.info("ОСТАЛОСЬ ИНТЕРНЕТА = " + user2.getInternet() + " ; ОСТАЛОСЬ МИНУТ = " + user2.getMinutes());
        if ((user2.getInternet() == 0.0) || (user2.getMinutes() == 0)) {
            userService.conditionOfResources(user2, true);
            //log.info("БЫЛ ПОТРАЧЕН ВЕСЬ ТРАФИК = " + user.getConditionOfResources());
        }
        return "redirect:/decrement";
    }


    @GetMapping("/appSettings")
    public String appWarnings(Model model, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "app_settings";
    }

    @PostMapping("/appSettings/change")
    public String appWarnings_change(Model model, Principal principal, Boolean resources, Boolean paymentTime) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("resources", resources);
        model.addAttribute("paymentTime", paymentTime);
        if ((resources == null)) {
            resources = false;
            userService.warningOfResources(user, resources);
        } else if (resources) {
            userService.warningOfResources(user, resources);
        }

        if ((paymentTime == null)) {
            paymentTime = false;
            userService.warningOfPayment(user, paymentTime);
        } else if (paymentTime) {
            userService.warningOfPayment(user, paymentTime);
        }

        return "redirect:/";
    }


    @Autowired
    private DefaultEmailService defaultEmailService;

    @PostMapping("/email/send")
    public String sendEmail(Model model, Principal principal, String email) {
        //String email = "letitop27@gmail.com";

        if ((userService.findUser(email) == null) || (email == null)) {
            model.addAttribute("errorEmailPas", "Пользователя с таким Email не существует.");
            return "email_password";
        }
        String answer = userService.emailChangePassword(email);
        defaultEmailService.sendSimpleEmail(email, "Сброс старого пароля в аккаунте MAMAPhone", "Новый пароль = " + answer);
        model.addAttribute("errorEmailPas", "Пароль успешно сброшен.");


        return "redirect:/login";
    }

    @GetMapping("/email")
    public String Email() {
        return "email_password";
    }

}
