package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.services.RateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController { //прием HTTP запросов
    private final RateService rateService;

    public WebController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/")
    public String billing(Model model) {
        model.addAttribute("rates", RateService.listRates());
        return "billing";
    }

    @GetMapping("/rate/{id}")
    public String rateInfo(@PathVariable Long id, Model model) {
        model.addAttribute("rate", rateService.getRateById(id));
        return "rate-info";
    }


    @PostMapping("/rate/create")
    public String createRate (Rate rate) {
        rateService.saveRate(rate);
        return "redirect:/"; //обновление страницы
    }

    @PostMapping("/rate/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        rateService.deleteRate(id);
        return "redirect:/"; //обновление страницы
    }
}
