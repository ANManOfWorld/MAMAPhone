package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Image;
import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.repositories.RateRepository;
import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service //выполнение бизнес-логики, выполнение вычислений и вызов внешних API
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
    private final UserRepository userRepository;

    public List<Rate> takeAll() {return rateRepository.findAll();}

    public List<Rate> listRates(String name) {
        if (name != null) return rateRepository.searchByName(name);
        return rateRepository.findAll();
    }

    public void saveRate(Rate rate) {     // До картинок
        //rate.setId(++id);
        //rates.add(rate);
        log.info("Saving new {}" + rate);
        rateRepository.save(rate);
    }

    // ---------------------------------------------- Фотографии
    public String saveRate(Rate rate,/* MultipartFile file, */Principal principal) throws IOException {
        //getUserByPrincipal(principal);
        if (rate.getPrice() > 99000) {
            return "Стоимость тарифа не может превышать 99000 руб.";
        }
        if (rate.getPrice() < 1) {
            return "Стоимость тарифа не может быть ниже 1 руб.";
        }
        if (rate.getCountOfMinutes() > 9999) {
            return "Количество минут не может превышать 9999 минут.";
        }
        if (rate.getCountOfTrafficInternet() > 9999) {
            return "Количество ГБ не может превышать 9999 ГБ.";
        }
        if (rate.getDescription().length() > 255) {
            return "Длина описания не может превышать 255 символов.";
        }
        if (rate.getName().length() > 25) {
            return "Количество символов в названии тарифа не может превышать 25.";
        }
       /* Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            rate.addImage(image);
        }*/
        log.info("Saving new rate. name: {} || description: {}", rate.getName(), rate.getDescription());
        rateRepository.save(rate);
        /*Rate rateFromBaseData = rateRepository.save(rate);
        if (rate.getPreviewImageId() != null) { // если нет картинки
            rateFromBaseData.setPreviewImageId(rateFromBaseData.getImages().get(0).getId());
            rateRepository.save(rate);
        }*/
        return "";
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
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
        Rate rate = getRateById(id);
        List<User> users = rate.getUsers();
        for (int i = 0; i < users.size(); i++) {
           //        users.get(i).setCalendar(null);
            users.get(i).setDateOfPayment(null);
            users.get(i).setCurrentRate(null);
        }
        rate.setUsers(null);
        rateRepository.deleteById(id);
    }

    public Rate getRateById(Long id) {
        /*for (Rate rate: rates) {
            if (rate.getId().equals(id)) return rate;
        }*/
        return rateRepository.findById(id).orElse(null);
    }
}
