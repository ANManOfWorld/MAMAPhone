package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j //логирование
@RequiredArgsConstructor
public class RateService {

    //private static List<Rate> rates = new ArrayList<>();
    /*private long id = 0;
    {
        rates.add(new Rate(++id,"На связи с близкими.", "Доступный базовый тариф для того, чтобы оставаться с близкими на связи.", 199, 200, 4));
        rates.add(new Rate(++id,"На связи с близкими ВСЕГДА.", "Дорогой тариф для поддержания связи с дорогими тебе людьми.", 899, 2000, 16));
    }*/
    private final RateRepository rateRepository;

    public List<Rate> listRates(String name) {
        if (name != null) return rateRepository.searchByName(name);
        return rateRepository.findAll();
    }

    public void saveRate(Rate rate) {
        //rate.setId(++id);
        //rates.add(rate);
        log.info("Saving new {}" + rate);
        rateRepository.save(rate);
    }

    public void deleteRate(Long id) {
        // rates.removeIf(rate -> rate.getId().equals(id));
        rateRepository.deleteById(id);
    }

    public Rate getRateById(Long id) {
        /*for (Rate rate: rates) {
            if (rate.getId().equals(id)) return rate;
        }*/

        return rateRepository.findById(id).orElse(null);
    }
}
