/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author azm
 */
@Entity
@Table(name = "CustomerDTO")
@Data
public class CustomerDTO implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @NotEmpty(message = "error.message.role_fk")
    @Length(max = 50, message = "error.message.length")
    @Column(name = "role_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private RoleDTO role_fk;

    @NotEmpty(message = "error.message.user_fk")
    @Length(max = 50, message = "error.message.length")
    @Column(name = "user_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserDTO user_fk;
}
