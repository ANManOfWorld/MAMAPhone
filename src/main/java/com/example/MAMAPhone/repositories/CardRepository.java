package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByNumOfCard(String numOfCard);
}
