/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.controller;

import com.azm.BuildRedditClone.dto.RegisterRequestDTO;
import com.azm.BuildRedditClone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController
{

    @Autowired
    private AuthService authService;
            
    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        authService.signup (registerRequestDTO);
        return new ResponseEntity<>("User Registration Successful",HttpStatus.OK);
    }

}
