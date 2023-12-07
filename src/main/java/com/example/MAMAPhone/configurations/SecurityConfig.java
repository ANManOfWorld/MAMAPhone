package com.example.MAMAPhone.configurations;

import com.example.MAMAPhone.services.OurUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//HERE!!
@EnableWebSecurity
@RequiredArgsConstructor //удаляет конструктор из класса
@Configuration

@EnableGlobalMethodSecurity(prePostEnabled = true) //для админ
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OurUserDetailsService ourUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/static/**" /*, "/images/**"*/)
                .permitAll()
                .antMatchers( "/admin/**", "/user/**", "/rate/create", "/rate/delete/**", "/billing").hasRole("ADMIN")
                .antMatchers( "/rate/create", "/rate/delete/**", "/billing").hasRole("MODERATOR") // УДАЛИТЬ
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/loginError")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ourUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}