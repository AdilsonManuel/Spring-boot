/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author azm
 */
@Entity
@Table(name = "Users")
@Data
public class UserDTO implements Serializable
{

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private String id;

    @NotEmpty(message = "error.name.empty")
    @Length(max = 50, message = "error.name.length")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "error.address.empty")
    @Length(max = 150, message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;

    @Email(message = "error.email.email")
    @NotEmpty(message = "error.email.empty")
    @Length(max = 80,message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;

    public UserDTO ()
    {
    }

    public UserDTO (String id, String name, String address, String email)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

}
