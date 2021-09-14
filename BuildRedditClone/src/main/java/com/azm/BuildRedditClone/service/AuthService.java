/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.service;

import com.azm.BuildRedditClone.dto.NotificationEmailDTO;
import com.azm.BuildRedditClone.dto.RegisterRequestDTO;
import com.azm.BuildRedditClone.dto.UserDTO;
import com.azm.BuildRedditClone.dto.VerificationTokenDTO;
import com.azm.BuildRedditClone.repository.UserRepository;
import com.azm.BuildRedditClone.repository.VerificationTokenRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author azm
 */
@Service
@AllArgsConstructor
public class AuthService
{
    
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private MailService mailService;

//    @Transactional for raltional databse
    public void signup (RegisterRequestDTO registerRequestDTO)
    {
        UserDTO userDTO = new UserDTO ();
        userDTO.setUserName (registerRequestDTO.getUsername ());
        userDTO.setEmail (registerRequestDTO.getEmail ());
        userDTO.setPassword (passwordEncoder.encode (registerRequestDTO.getPassword ()));
        userDTO.setCreated (Instant.now ());
        userDTO.setEnabled (false);

        userRepository.save (userDTO);

        String token = genereteVerificationToke (userDTO);
        mailService.sendEmail (new NotificationEmailDTO ("Please activate your Account", userDTO.getEmail (), "Thank you for signing up to Spring Reddit"
                + "Please check on the below url to activate your account : "
                + "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String genereteVerificationToke (UserDTO userDTO)
    {
        String token = UUID.randomUUID ().toString ();
        VerificationTokenDTO verificationTokenDTO = new VerificationTokenDTO ();
        verificationTokenDTO.setToken (token);
        verificationTokenDTO.setUserDTO (userDTO);

        verificationTokenRepository.save (verificationTokenDTO);
        return token;
    }

}
