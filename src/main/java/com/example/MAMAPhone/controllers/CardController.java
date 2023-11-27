package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Card;
import com.example.MAMAPhone.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller //связь между компонентами и выполнение действий согласно переданных запросов
@RequiredArgsConstructor //удаляет конструктор из класса
public class CardController {


    private final CardService cardService;
    final String NUM_OF_CARD = "[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
    final String CVC = "[0-9][0-9][0-9]";

    @GetMapping("/card")
    public String admin(Model model) {
        model.addAttribute("cards", cardService.list());
        return "card";
    }

    @PostMapping("/card/create")
    public String createCard(@ModelAttribute("card") Card card, Double balance, Principal principal, Model model) {
        String num = card.getNumOfCard();
        String cvc = card.getCVC();
        if ((!cardService.saveCard(card)) && (num.matches(NUM_OF_CARD)) && (cvc.matches(CVC))) {
            model.addAttribute("errorMessage", "Карта с таким номером: " + card.getNumOfCard() + " уже существует");
            return "card";
        }
        return "redirect:/card";
    }

    @PostMapping("/card/update/{id}")
    public String updateBalance(@PathVariable("id") Long id, @ModelAttribute("card") Card card, Integer balance, Model model) {
        model.addAttribute("balance", balance);
        cardService.updateBalanceCard(id, balance);
        return "redirect:/card";
    }

    @PostMapping("/card/delete/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "redirect:/card"; //обновление страницы
    }
}
