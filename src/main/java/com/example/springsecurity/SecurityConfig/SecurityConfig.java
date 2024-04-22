package com.example.springsecurity.SecurityConfig;

import com.example.springsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService); //username
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    //all api(s) should be covered
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register","/api/v1/auth/login").permitAll()
                .requestMatchers("/api/v1/auth/users","/api/v1/auth/user/delete/{username}","api/v1/todo/todos").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/auth/user/update/{username}").hasAnyAuthority("ADMIN","CUSTOMER")
                .requestMatchers("api/v1/todo/user-todos", "api/v1/todo/add","api/v1/todo/update/{todo_id}","/api/v1/todo/delete/{todo_id}").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();

    }
}
