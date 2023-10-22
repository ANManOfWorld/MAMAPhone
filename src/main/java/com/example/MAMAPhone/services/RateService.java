package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Image;
import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    /*public void saveRate(Rate rate) {     // До картинок
        //rate.setId(++id);
        //rates.add(rate);
        log.info("Saving new {}" + rate);
        rateRepository.save(rate);
    }*/

    // ---------------------------------------------- Фотографии
    public void saveRate(Rate rate, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            rate.addImage(image);
        }
        log.info("Saving new rate. name: {} || description: {}", rate.getName(), rate.getDescription());
        Rate rateFromBaseData = rateRepository.save(rate);
        rateFromBaseData.setPreviewImageId(rateFromBaseData.getImages().get(0).getId());
        rateRepository.save(rate);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    // ---------------------------------------------- Фотографии

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
