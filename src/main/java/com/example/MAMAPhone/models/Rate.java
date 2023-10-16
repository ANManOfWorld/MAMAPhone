package com.example.MAMAPhone.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rate {
    private Long id;
    private String name;
    private String description;
    private int price;
    private int countOfMinutes;
    private int countOfTrafficInternet;

}
