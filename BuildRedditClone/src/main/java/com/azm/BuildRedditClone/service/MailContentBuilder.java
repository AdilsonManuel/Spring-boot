/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author azm
 */
@Service
@AllArgsConstructor
public class MailContentBuilder
{
    private final TemplateEngine templateEngine;
    
    String build(String message){
        Context context = new Context ();
        context.setVariable("messgae",message);
        return templateEngine.process("mailTemplate",context);
    }
}
