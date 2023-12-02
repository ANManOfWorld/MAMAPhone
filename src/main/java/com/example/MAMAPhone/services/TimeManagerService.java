package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.TimeManager;
import com.example.MAMAPhone.repositories.TimeManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimeManagerService {
    private final TimeManagerRepository timeManagerRepository;

    public void createTimeManager(TimeManager timeManager, Long id) {
        //Long id = timeManager.getId();
        log.info("TimeManager with id = " + id + " был найден и изменён/создан.");
        TimeManager time = timeManagerRepository.searchById(id);
        time.setSeconds(timeManager.getSeconds());
        time.setMinutes(timeManager.getMinutes());
        time.setHours(timeManager.getHours());
        time.setDays(timeManager.getDays());
        time.setMonth(timeManager.getDays());

        timeManagerRepository.save(timeManager);
    }

    public TimeManager findTimeManager(Long id) {
        return timeManagerRepository.searchById(id);
    }

    public List<TimeManager> list() {
        return timeManagerRepository.findAll();
    }
}
