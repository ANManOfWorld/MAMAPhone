package com.example.MAMAPhone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rates")
@Data //аннотация сгенирует при компиляции необходимый код от LOMBOK
@AllArgsConstructor
@NoArgsConstructor //чтобы не создавать пустой конструктор класса
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "countOfMinutes")
    private int countOfMinutes;
    @Column(name = "countOfTrafficInternet")
    private int countOfTrafficInternet;

    //------------------------------- Фотографии
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rate") //LAZY - при загрузке родительской сущности, дочерняя сущность загружена не будет
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


    public void addImage(Image image) {
        image.setRate(this);
        images.add(image);
    }
    //-------------------------------- Фотографии
}
