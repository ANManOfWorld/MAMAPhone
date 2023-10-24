package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.services.RateService;
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


    public WebController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/")
    public String billing(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
        model.addAttribute("rates", rateService.listRates(name));
        model.addAttribute("user", rateService.getUserByPrincipal(principal));
        return "billing";
    }

    @GetMapping("/rate/{id}")
    public String rateInfo(@PathVariable Long id, Model model) {
        Rate rate = rateService.getRateById(id);
        model.addAttribute("rate", rate);
        model.addAttribute("images", rate.getImages());  // Фотография функция по передаче её в "Подробнее"
        return "rate-info";
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
    public String deleteProduct(@PathVariable Long id) {
        rateService.deleteRate(id);
        return "redirect:/"; //обновление страницы
    }
}
