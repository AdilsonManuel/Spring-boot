/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.service;

import com.apress.azm.UserRegistrationSystem.DTO.RoleDTO;
import com.apress.azm.UserRegistrationSystem.DTO.UserDTO;
import java.util.List;

/**
 *
 * @author azm
 */
public interface UserService
{
    UserDTO saveUser(UserDTO user);
    RoleDTO saveRole(RoleDTO user);
    void addRoleToUser(String userName, String  roleName);
    UserDTO getUser(String userName);
    List<UserDTO> getUsers();
}
