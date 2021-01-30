package com.kodem.demo.linkedindemo.authentication.config;

import com.kodem.demo.linkedindemo.authentication.filter.JwtTokenFilter;
import com.kodem.demo.linkedindemo.authentication.filter.JwtUserAuthFilter;
import com.kodem.demo.linkedindemo.authentication.services.UserDtlsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDtlsService userDtlsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Setting UserDetailService and passcode encoder
        auth.userDetailsService(userDtlsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() // Will not be used because we are dealing with RestAPI
                .requestCache().disable() // Don't save any session information on client side
                // Don't use Session Management
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Adding Filter to validate login requests
                .addFilter(new JwtUserAuthFilter(authenticationManager()))
                // Adding filter to validate JWT
                .addFilterAfter(new JwtTokenFilter(), JwtUserAuthFilter.class)          
                .authorizeRequests()
                // Allow Anyone Paths
                .antMatchers("/signup", "/login", "/company", "/institute", "/language").permitAll()
                // Restrict other urls
                .anyRequest().authenticated();
    }
}
