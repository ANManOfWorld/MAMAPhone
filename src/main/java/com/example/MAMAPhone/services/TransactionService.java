package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Card;
import com.example.MAMAPhone.models.Transaction;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.repositories.CardRepository;
import com.example.MAMAPhone.repositories.TransactionRepository;
import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardService cardService;

    public boolean createTransaction(User user, Card card, Integer balance) { //регистрация
        Long idUser = user.getId();
        Long idCard = card.getId();
        if (idCard != null || idUser != null) {
            Transaction transaction  = new Transaction();
            log.info("карта была НАЙДЕНА!");
            if (card.getBalance() >= balance) {
                transaction.setIdUser(idUser);
                transaction.setIdCard(idCard);
                transaction.setSum(balance);
                log.info("Create a transaction with idUser = " + idUser + "; idCard = " + idCard + "; с суммой = " + balance);
                transactionRepository.save(transaction);
                return true;
            } else {
                log.info("Недостаточно средств на карте!");
                return false;
            }
        } else {
            log.info("КАРТА НЕ БЫЛА НАЙДЕНА!!");
            return false;
        }
    }

}
