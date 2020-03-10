package com.mkolongo.heros.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/img/**").permitAll()
                .antMatchers("/", "/users/login", "/users/register").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/users/login")
                .defaultSuccessUrl("/users/home")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/");
    }
}
