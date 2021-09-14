/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.service;

import com.azm.BuildRedditClone.dto.NotificationEmailDTO;
import com.azm.BuildRedditClone.exception.SpringRedditException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author azm
 */
@Service
@AllArgsConstructor
@Slf4j
public class MailService
{
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    
    void sendEmail(NotificationEmailDTO notificationEmailDTO)
    {
        MimeMessagePreparator messagePreparator = mimeMessage ->{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationEmailDTO.getRecipient ());
            messageHelper.setSubject(notificationEmailDTO.getSubject ());
            messageHelper.setText(mailContentBuilder.build (notificationEmailDTO.getBody ()));
        };
        try
        {
            mailSender.send(messagePreparator);
            log.info ("Activation email sent!!");
        }
        catch (MailException e)
        {
            throw new SpringRedditException("Exception occured when sending mail to " + notificationEmailDTO.getRecipient ());
        }
    }
}
