package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Card;
import com.example.MAMAPhone.models.Transaction;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.repositories.CardRepository;
import com.example.MAMAPhone.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public String createTransaction(User user, String numOfCard, String currentCVC, Integer balance) { //регистрация
        Long idUser = user.getId();
        /*Long idCard = card.getId();*/
        Card card = cardRepository.findByNumOfCard(numOfCard);
        if (card == null) {
            return "Номер карты не существует.";
        }
        if (!card.getCVC().equals(currentCVC)) {
            return "Неправильный CVC.";
        }
        if ((card.getBalance() <= 0) || (card.getBalance() < balance)) {
            return "Недостаточно средств на карте. Выберите другую карту.";
        }
        if (card.getId() != null || idUser != null) {
            Transaction transaction  = new Transaction();
            log.info("карта была НАЙДЕНА!");
            if (card.getBalance() >= balance) {
                transaction.setIdUser(idUser);
                transaction.setIdCard(card.getId());
                transaction.setSum(balance);
                log.info("Create a transaction with idUser = " + idUser + "; idCard = " + card.getId() + "; с суммой = " + balance);
                transactionRepository.save(transaction);
            } else {
                log.info("Недостаточно средств на карте!");
            }
        } else {
            log.info("КАРТА НЕ БЫЛА НАЙДЕНА!!");
        }
        return "";
    }
}
