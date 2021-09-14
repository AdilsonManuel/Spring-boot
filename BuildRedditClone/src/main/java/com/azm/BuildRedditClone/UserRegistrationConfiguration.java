/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 *
 * @author azm
 */
@Configuration
public class UserRegistrationConfiguration
{

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource ()
    {
        ReloadableResourceBundleMessageSource messaBundle
                = new ReloadableResourceBundleMessageSource();
        messaBundle.setBasename("classpath:messages/messages");
        messaBundle.setDefaultEncoding("UTF-8");
        return messaBundle;
    }
}
