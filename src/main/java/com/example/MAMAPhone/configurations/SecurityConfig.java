package com.example.MAMAPhone.configurations;

/*@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //удаляет конструктор из класса
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;
    private final OurUserDetailsService ourUserDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration").permitAll()
                        .requestMatchers("/rate/**", "/image/**")
                        .hasAnyAuthority("ROLE_MODERATOR","ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, OurUserDetailsService ourUserDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(ourUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder(8))
                .and()
                .build();
    }

    *//*@Bean
    public JdbcUserDetailsManager user(PasswordEncoder encoder) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }*//*

    *//*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ourUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }*//*

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}*/


import com.example.MAMAPhone.services.OurUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//HERE!!
@EnableWebSecurity
@RequiredArgsConstructor //удаляет конструктор из класса
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OurUserDetailsService ourUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", /*"/rate/**",*/ "/images/**", "/registration")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

   /* @Autowired
    protected void configGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(ourUserDetailsService).passwordEncoder(passwordEncoder());
    }
*/
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