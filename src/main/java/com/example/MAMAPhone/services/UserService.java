package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.Rate;
import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.models.enums.Role;
import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) { //регистрация
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ROLE_ADMIN);     // ROLE_USER   ROLE_ADMIN
            log.info("Saving new User with email {}", email);
            userRepository.save(user); //запись юзера в бд
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    //----admin
    public List<User> list() {
        return userRepository.findAll();
    }

    public void userBan(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("User was banned; email: {}", user.getEmail());
            } else {
                user.setActive(true);
                log.info("User was Unbanned; email: {}", user.getEmail());
            }

        }
        userRepository.save(user);
    }



    public void moderator(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            if (!user.getRoles().contains(Role.ROLE_MODERATOR)) {
                user.getRoles().add(Role.ROLE_MODERATOR);
                log.info("User was made a status of Moderator; email: {}", user.getEmail());
            } else {
                user.getRoles().remove(Role.ROLE_MODERATOR);
                log.info("User was delete a status of Moderator; email: {}", user.getEmail());
            }
            userRepository.save(user);
        }
    }

    public void administrator(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            if (!user.getRoles().contains(Role.ROLE_ADMIN)) {
                user.getRoles().add(Role.ROLE_ADMIN);
                log.info("User was made a status of Admin; email: {}", user.getEmail());
            } else {
                user.getRoles().remove(Role.ROLE_ADMIN);
                log.info("User was delete a status of Admin; email: {}", user.getEmail());
            }
            userRepository.save(user);
        }
    }



    public String chooseRate(User user, Rate rate) {
        if (user.getBalance() >= rate.getPrice()) {
            user.chooseRate(rate);
            user.setInternet(rate.getCountOfTrafficInternet());
            user.setMinutes(rate.getCountOfMinutes());
            user.setBalance(user.getBalance() - rate.getPrice());


            user.setCalendar(new GregorianCalendar());               //привязка даты подключения тарифа
            userRepository.save(user);
            log.info("Дата когда подключили = " + user.getCalendar().getTime());
            Calendar payment = new GregorianCalendar();
            //payment.add(Calendar.SECOND,5);
            log.info("Дата оплаты = " + payment.getTime());
            user.setDateOfPayment(payment);
            userRepository.save(user);
            log.info("ПРОВЕРКА PAYMENT!!!!! = " + user.getDateOfPayment().getTime());
            return "Тариф успешно изменён";
        } else {
            return "Недостаточно средств на балансе для изменения тарифа.";
        }
    }

    public String topUpBalance(User user, Integer sum) {
        if (sum > 0) {
            user.setBalance(user.getBalance() + sum);
            userRepository.save(user);
        } else {
            return "Баланс не больше 0.";
        }
        return "";
    }

    public void changeCVC(User user, String CVC) {
        user.setCVC(CVC);
        userRepository.save(user);
    }

    public void changeNumOfCard(User user, String numOfCard) {
        user.setNumOfCard(numOfCard);
        userRepository.save(user);
    }


    public String changePassword(User user, String oldPassword, String newPassword) {
        log.info("(!из функции с encoder) Новый пароль: " + newPassword + "; Старый пароль: " + oldPassword);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "Старый пароль введён неверно!";
        }
        log.info("Был хеш: " + user.getPassword());
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Стал хеш: " + user.getPassword());
        return "Пароль успешно изменён!";
    }




    /*ИЗМЕНЕНИЕ ПЕРСОНАЛЬНЫХ ДАННЫХ*/

    public String changeName (User user, String name) {
        log.info("Пришедшее имя: " + name);
        if (name == "") {
            return "Имя не должно быть пустым";
        }
        if (!((name.length() <= 12) && (name.length() >= 2))) {
            return "Имя должно быть не менее 2 символов и не более 12";
        }

        if (!user.getName().equals(name)) {
            user.setName(name);
            userRepository.save(user);
        } else {
            log.info("Пришедшее имя: " + name + " == (уже установленному) " + user.getName());
        }
        return "";
    }

    public String changeLastName (User user, String lastName) {
        log.info("Пришедшая фамилия: " + lastName);
        if (lastName.equals("")) {
            return "Фамилия не должна быть пустой";
        }
        if (!((lastName.length() <= 12) && (lastName.length() >= 2))) {
            return "Фамилия должна быть не менее 2 символов и не более 12";
        }

        if (!user.getLastName().equals(lastName)) {

            user.setLastName(lastName);
            userRepository.save(user);
        } else {
            log.info("Пришедшая фамилия: " + lastName + " == (уже установленной) " + user.getLastName());
        }
        return "";
    }

    public String changeFartherName (User user, String fartherName) {
        log.info("Пришедшее отчество: " + fartherName);
        if (fartherName.equals("")) {
            return "";
        }

        if (!((fartherName.length() <= 12) && (fartherName.length() >= 2))) {
            return "Отчество должно быть не менее 2 символов и не более 12";
        }

        if (!user.getFatherName().equals(fartherName)) {

            user.setFatherName(fartherName);
            userRepository.save(user);
        } else {
            log.info("Пришедшее отчёство: " + fartherName + " == (уже установленному) " + user.getFatherName());
        }
        return "";
    }

    /*ДОПИСАТЬ ДАТУ!!!*/

    /*String EMAIL_TEMPLATE = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";*/
    String EMAIL_TEMPLATE = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";
    public String changeEmail (User user, String email) {
        log.info("Пришедший email: " + email);
        if (email.equals("")) {
            return "Email не должен быть пустым";
        }
        if (!email.matches(EMAIL_TEMPLATE)) {
            return "Введите email правильно";
        }


        if (!user.getEmail().equals(email)) {
            if (userRepository.findByEmail(email) != null) {
                log.info("Эл. почта " + email + " занята!");
                return "Эта эл. почта занята. Выберите другую.";
            }
            user.setEmail(email);
            userRepository.save(user);
            return "good";
        } else {
            log.info("Пришедший email: " + email + " == (уже установленному) " + user.getEmail());
            return "";
        }
        //return "";
    }

    public void changeInternet(Long id, Double internet) {
        User user = userRepository.findById(id).orElse(null);
        if ((user.getInternet() >= internet) || (internet <= 0)) {
            user.setInternet(user.getInternet() - internet);
            userRepository.save(user);
        }
    }

    public void changeMinutes(Long id, Integer minutes) {
        User user = userRepository.findById(id).orElse(null);
        if ((user.getMinutes() >= minutes) || (minutes <= 0)) {
            user.setMinutes(user.getMinutes() - minutes);
            userRepository.save(user);
        }
    }



   /* public void changePersonalData(User user, String name, String lastName, String fatherName, Date date) {
        log.info("Данные: Имя = " + name + " ; Фамилия = " + lastName + " ; Отчество = " + " ; Дата = " + date + " ; Email = " + user.getEmail());

        log.info("Пришедшее имя: " + name);
        if (!user.getName().equals(name)) {

            user.setName(name);
            //userRepository.save(user);
        } else {
            log.info("Пришедшее имя: " + name + " == (уже установленному)" + user.getName());
        }

        log.info("Пришедшая фамилия: " + lastName);
        if (!user.getLastName().equals(lastName)) {

            user.setLastName(lastName);
            //userRepository.save(user);
        } else {
            log.info("Пришедшая фамилия: " + lastName + " == (уже установленной)" + user.getLastName());
        }

        log.info("Пришедшее отчество: " + fatherName);
        if (!user.getFatherName().equals(fatherName)) {

            user.setFatherName(fatherName);
            //userRepository.save(user);
        } else {
            log.info("Пришедшее отчёство: " + fatherName + " == (уже установленному)" + user.getFatherName());
        }

        userRepository.save(user);
    }
*/
            /*ИЗМЕНЕНИЕ ПЕРСОНАЛЬНЫХ ДАННЫХ*/



}
