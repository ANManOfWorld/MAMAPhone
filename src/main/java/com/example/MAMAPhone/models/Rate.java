package com.example.MAMAPhone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity // класс является сущностью и будет сохраняться в БД
@Table(name = "rates")
@Data //аннотация сгенирует при компиляции необходимый код от LOMBOK
@AllArgsConstructor
@NoArgsConstructor //чтобы не создавать пустой конструктор класса
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "changeInformationRateFlag")
    private Boolean changeInformationRateFlag = false;

    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "countOfMinutes")
    private int countOfMinutes;
    @Column(name = "countOfTrafficInternet")
    private Double countOfTrafficInternet;

    //------------------------------- Фотографии
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rate") //LAZY - при загрузке родительской сущности, дочерняя сущность загружена не будет
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;;



    /*@OneToOne(optional = false, mappedBy="rate")
    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "currentRate") //LAZY - при загрузке родительской сущности, дочерняя сущность загружена не будет
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }





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
