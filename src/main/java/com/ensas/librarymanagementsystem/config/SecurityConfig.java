package com.ensas.librarymanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/css/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/js/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/uh").hasRole("PATIENT");
        http.authorizeHttpRequests().antMatchers("/uh").hasRole("DOCTOR");
        http.authorizeHttpRequests().antMatchers("/uh").hasRole("ADMIN");
        http.authorizeHttpRequests().anyRequest().authenticated();
    }
}
