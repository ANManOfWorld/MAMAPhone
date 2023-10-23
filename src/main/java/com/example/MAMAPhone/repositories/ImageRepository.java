package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.Image;
import org.springframework.data.jpa.repository.JpaRepository; //базовый набор методов для создания, обновления,
                                                             // удаления и выборки данных

public interface ImageRepository extends JpaRepository<Image, Long> {  //работа с поиском, а также для получения и хранения данных
                                                                        // (сущность, которая хранится; обращение)
}
