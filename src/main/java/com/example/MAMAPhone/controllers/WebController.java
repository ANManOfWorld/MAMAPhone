package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.services.RateService;
import com.example.MAMAPhone.services.UserService;
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



    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }
    @GetMapping("/personalData")
    public String personalData() {
        return "personal_data";
    }

    @GetMapping("/security")
    public String security(Model model, Principal principal) {
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "security";
    }

    @GetMapping("/top_up_balance")
    public String appSettings(Model model, Principal principal) {
        User user = rateService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "topUpBalance";
    }

}
