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
            user.getRoles().add(Role.ROLE_USER);     // ROLE_USER   ROLE_ADMIN
            log.info("Saving new User with email {}", email);
            userRepository.save(user); //запись юзера в бд
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {throw new UsernameNotFoundException("User not found");}
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

    public void chooseRate (User user, Rate rate) {
        user.chooseRate(rate);
        user.setInternet(rate.getCountOfTrafficInternet());
        user.setMinutes(rate.getCountOfMinutes());
        userRepository.save(user);
    }

    public void topUpBalance(User user, Double sum) {
        user.setBalance(sum);
        userRepository.save(user);
    }
}
