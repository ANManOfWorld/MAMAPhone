package com.example.MAMAPhone.controllers;


import com.example.MAMAPhone.models.TimeManager;
import com.example.MAMAPhone.services.TimeManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //связь между компонентами и выполнение действий согласно переданных запросов
@RequiredArgsConstructor //удаляет конструктор из класса
@Slf4j
public class TimeManagerController {

    private final TimeManagerService timeManagerService;


    @GetMapping("/timeManager")
    public String getTimeManager(Model model) {
        model.addAttribute("timeManagers", timeManagerService.list());
        return "timeManager";
    }

    @PostMapping("/timeManager/create/{id}")
    public String changeTimeManager(@PathVariable("id") Long id, @ModelAttribute("time") TimeManager timeManager, Model model) {
        timeManagerService.createTimeManager(timeManager, id);
        return "redirect:/timeManager";
    }

    /*@PostMapping("/timeManager/create")                                                       //для создания первого таймера
    public String createTimeManager(@ModelAttribute("time") TimeManager timeManager, Model model) {
        timeManagerService.createTimeManager(timeManager);
        return "redirect:/timeManager";
    }*/
}
