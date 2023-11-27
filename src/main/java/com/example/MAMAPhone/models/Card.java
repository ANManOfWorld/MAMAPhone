package com.example.MAMAPhone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity // класс является сущностью и будет сохраняться в БД
@Table(name = "cards")
@Data //аннотация сгенирует при компиляции необходимый код от LOMBOK
@AllArgsConstructor
@NoArgsConstructor //чтобы не создавать пустой конструктор класса
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "numOfCard")
    private String numOfCard;

    @Column(name = "CVC")
    private String CVC;

    @Column(name = "balance")
    @Value("0.0")
    private Integer balance = 0;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(String numOfCard) {
        this.numOfCard = numOfCard;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
