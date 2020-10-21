package org.okten.javaadvanced.config;

import org.okten.javaadvanced.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and().and().userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//        auth.authenticationProvider(daoAuthenticationProvider());
    }

    // DaoAuthenticationProvider - провайдер, який автентифікує юзерів використовуючи базу даних.
    // userDetailsService - механізм пошуку юзерів.
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //CSRF i CORS потрібно деактивовувати, або ж надсилати ріквести з відповідними токенами
        http.csrf().disable().cors().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/directors").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users").anonymous()
                .antMatchers(HttpMethod.GET).authenticated()
                .and().httpBasic();
    }
}
