package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Rate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateService {
    private static List<Rate> rates = new ArrayList<>();
    private long id = 0;
    {
        rates.add(new Rate(++id,"На связи с близкими.", "Доступный базовый тариф для того, чтобы оставаться с близкими на связи.", 199, 200, 4));
        rates.add(new Rate(++id,"На связи с близкими ВСЕГДА.", "Дорогой тариф для поддержания связи с дорогими тебе людьми.", 899, 2000, 16));
    }

    public static List<Rate> listRates() {return rates;}

    public void saveRate(Rate rate) {
        rate.setId(++id);
        rates.add(rate);
    }

    public void deleteRate(Long id) {
        rates.removeIf(rate -> rate.getId().equals(id));
    }

    public Rate getRateById(Long id) {
        for (Rate rate: rates) {
            if (rate.getId().equals(id)) return rate;
        }
        return null;
    }
}
