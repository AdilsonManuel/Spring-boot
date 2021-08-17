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
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author azm
 */
@Entity
@Table(name = "users")
@Data
public class UserInfo implements Serializable
{

    @Id
    @GeneratedValue
    @Column(name = "userid")
    private String id;

    @Column(name = "userName")
    @NotEmpty
    private String userName;
    

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "role")
    private String role;

    public UserInfo (String id, String userName, String password, boolean isEnabled, String role)
    {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.role = role;
    }

    public UserInfo ()
    {
    }

}
