/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

/**
 *
 * @author azm
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable
{
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String userName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleDTO> roles = new ArrayList<>();
    
}
