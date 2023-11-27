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



    public void chooseRate(User user, Rate rate) {
        if (user.getBalance() >= rate.getPrice()) {
            user.chooseRate(rate);
            user.setInternet(rate.getCountOfTrafficInternet());
            user.setMinutes(rate.getCountOfMinutes());
            user.setBalance(user.getBalance() - rate.getPrice());
            userRepository.save(user);
        } /*ДОПИСАТЬ ОШИБКУ */
    }

    public void topUpBalance(User user, Integer sum) {
        if (sum > 0) {
            user.setBalance(user.getBalance() + sum);
            userRepository.save(user);
        }
    }

    public void changeCVC(User user, String CVC) {
        user.setCVC(CVC);
        userRepository.save(user);
    }

    public void changeNumOfCard(User user, String numOfCard) {
        user.setNumOfCard(numOfCard);
        userRepository.save(user);
    }


}
