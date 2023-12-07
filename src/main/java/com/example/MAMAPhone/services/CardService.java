package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Card;
import com.example.MAMAPhone.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //логирование
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public boolean saveCard(Card card) {
        String num = card.getNumOfCard();
        if (cardRepository.findByNumOfCard(num) != null) {
            return false;
        } else {
            cardRepository.save(card);
            return true;
        }
    }

    public String updateBalanceCard(Long id, Integer balance) {
        Card card = cardRepository.findById(id).orElse(null);
        log.info("1) номер карты: " + card.getNumOfCard() + ", ПРИШЛА СУММА: " + balance);
        if (cardRepository.findById(id) == null) {
            return "Номер карты не существует.";
        } else {
            card.setBalance(card.getBalance() + balance);
            log.info("2) Баланс на карте: ", card.getBalance());
            cardRepository.save(card);
        }
        return "";
    }

    public void deleteCard(Long id) {
        // rates.removeIf(rate -> rate.getId().equals(id));
        cardRepository.deleteById(id);
    }

    public List<Card> list() {
        return cardRepository.findAll();
    }


    public Card loadCardByNumOfCard(String numOfCard) /*throws Exception*/{
        Card card = cardRepository.findByNumOfCard(numOfCard);
        if (card == null) {
            /*throw new Exception("Card was not FOUND!");*/
            log.info("CARD WASN'T FOUND!!");
        }
        return card;
    }
}
