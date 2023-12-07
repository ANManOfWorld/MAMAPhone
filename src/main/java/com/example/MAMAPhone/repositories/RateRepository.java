package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> { //получение, сохранение данных и пр. дабы не прописывать свою логику взаимодействия с бд
    /*List<Rate>*/Rate searchByName(String name);                           // (сущность, которая хранится; обращение)
    /*Rate searchByRateName(String name);*/

}
