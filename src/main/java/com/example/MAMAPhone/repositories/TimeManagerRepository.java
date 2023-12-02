package com.example.MAMAPhone.repositories;

import com.example.MAMAPhone.models.TimeManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeManagerRepository extends JpaRepository<TimeManager, Long> {
    //TimeManager findTimeManager(Long id);
    TimeManager searchById(Long id);
}
