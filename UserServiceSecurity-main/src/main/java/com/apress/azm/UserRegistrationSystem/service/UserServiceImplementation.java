/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.service;

import com.apress.azm.UserRegistrationSystem.DTO.RoleDTO;
import com.apress.azm.UserRegistrationSystem.DTO.UserDTO;
import com.apress.azm.UserRegistrationSystem.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apress.azm.UserRegistrationSystem.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author azm
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService
{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    
    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException
    {
        UserDTO userDTO = userRepository.findByName (userName);
        if (userDTO == null)
        {
           
            log.error ("User Not Found in the database");
            throw new UsernameNotFoundException ("User not found in the database");
        }
        else
        {
            log.info ("User Found in the database {}", userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<> ();
        userDTO.getRoles ().forEach (role -> {
            authorities.add (new SimpleGrantedAuthority (role.getName ()));
         });
        return new org.springframework.security.core.userdetails.User (userDTO.getUserName (), userDTO.getPassword (), authorities);

    }

    @Override
    public UserDTO saveUser (UserDTO user)
    {
        log.info ("Saving new user {} to the database", user.getUserName ());
        user.setPassword (passwordEncoder.encode (user.getPassword ()));
        return userRepository.save (user);
    }

    @Override
    public RoleDTO saveRole (RoleDTO roleDTO)
    {
        log.info ("Saving new role {} to the database", roleDTO.getName ());

        return roleRepository.save (roleDTO);
    }

    @Override
    public void addRoleToUser (String userName, String roleName)
    {
        log.info ("Adding role {} to user {}", roleName, userName);

        UserDTO userDTO = userRepository.findByName (userName);
        RoleDTO roleDTO = roleRepository.findByName (roleName);

        userDTO.getRoles ().add (roleDTO);
    }

    @Override
    public UserDTO getUser (String userName)
    {
        log.info ("Fetching user {}", userName);

        return userRepository.findByName (userName);
    }

    @Override
    public List<UserDTO> getUsers ()
    {
        log.info ("Fetching all Users ");

        return userRepository.findAll ();
    }

}
