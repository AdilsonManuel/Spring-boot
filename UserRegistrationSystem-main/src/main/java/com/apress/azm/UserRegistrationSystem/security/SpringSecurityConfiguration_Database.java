/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.security;

import com.apress.azm.UserRegistrationSystem.Service.UserInfoDetailsService;
import com.apress.azm.UserRegistrationSystem.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 *
 * @author azm
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration_Database extends WebSecurityConfigurerAdapter
{

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;
    
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Bean
    public static NoOpPasswordEncoder passwordEncoder ()
    {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance ();
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception
    {
//        System.out.println (" -> "+userInfoRepository.findByName ("user"));
        auth.userDetailsService (userInfoDetailsService);
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception
    {
        http.sessionManagement ().sessionCreationPolicy (SessionCreationPolicy.STATELESS)
                .and ()
                .authorizeRequests ()
                .antMatchers ("/api/user/**")
                .authenticated ()
                .and ()
                .httpBasic ()
                .realmName ("User Registration System")
                .and ()
                .csrf ()
                .disable ();
    }

}
