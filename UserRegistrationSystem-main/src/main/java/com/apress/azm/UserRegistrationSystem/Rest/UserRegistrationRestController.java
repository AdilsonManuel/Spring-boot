/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.Rest;

import com.apress.azm.UserRegistrationSystem.dto.UserDTO;
import com.apress.azm.UserRegistrationSystem.exception.CustomErrorType;
import com.apress.azm.UserRegistrationSystem.repository.UserRepository;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController
{

    public static final Logger logger = LoggerFactory.getLogger (UserRegistrationRestController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers ()
    {
        List<UserDTO> users = userRepository.findAll ();

        if (users.isEmpty ())
        {
            return new ResponseEntity<List<UserDTO>> (HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>> (users, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser (@Valid @RequestBody final UserDTO userDTO)
    {
        logger.info ("Creating User: {}", userDTO);
        if (userRepository.findByName (userDTO.getName ()) != null)
        {
            logger.error ("Unable to create. A user with name{} already exist", userDTO.getName ());
            return new ResponseEntity<UserDTO> (new CustomErrorType ("Unable to create new user. A user with name " + userDTO.getName () + " already exist."), HttpStatus.CONFLICT);
        }
        userRepository.save (userDTO);
        return new ResponseEntity<UserDTO> (userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("id") final String id)
    {
        UserDTO userDTO = userRepository.findByID (id);

        if (userDTO == null)
        {
            return new ResponseEntity<UserDTO> (new CustomErrorType ("User with id" + id + "Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO> (userDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser (@PathVariable("id") final String id, @RequestBody UserDTO userDTO)
    {
        UserDTO currentUserDTO = userRepository.findByID (id);

        if (currentUserDTO == null)
        {
            return new ResponseEntity<UserDTO> (new CustomErrorType ("Unable to update. User with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }

        //Update currentUser data with user data
        currentUserDTO.setName (userDTO.getName ());
        currentUserDTO.setAddress (userDTO.getAddress ());
        currentUserDTO.setEmail (userDTO.getEmail ());

        userRepository.save (currentUserDTO);

        return new ResponseEntity<UserDTO> (currentUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> deleteUser (@PathVariable("id") final String id)
    {

        UserDTO userDTO = userRepository.findByID (id);

        if (userDTO == null)
        {
            return new ResponseEntity<UserDTO> (new CustomErrorType ("Unable to delete. User with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById (id);

        return new ResponseEntity<UserDTO> (HttpStatus.NO_CONTENT);
    }

}
