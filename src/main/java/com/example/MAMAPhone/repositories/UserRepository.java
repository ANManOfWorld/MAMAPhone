package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;//базовый набор методов для создания, обновления,
                                                            // удаления и выборки данных

public interface UserRepository extends JpaRepository<User, Long> { //работа с поиском, а также для получения и хранения данных
    User findByEmail(String email);                                 // (сущность, которая хранится; обращение)
}
