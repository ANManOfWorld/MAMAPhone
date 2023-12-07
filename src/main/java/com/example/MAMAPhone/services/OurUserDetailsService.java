package com.example.MAMAPhone.services;

import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OurUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //авторизиация
        var user = userRepository.findByEmail(email);
        if (user == null) {
            //log.info("ЗДЕСЬ БЫЫЫЫЫЫЛ!");
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return userRepository.findByEmail(email);
    }
}
