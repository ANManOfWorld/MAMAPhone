package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.models.enums.Role;
import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActive(true);
            user.getRoles().add(Role.ROLE_USER);
            log.info("Saving new User with email {}", email);
            return true;
        }
    }
}
