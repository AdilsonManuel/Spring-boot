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
@Table(name = "UserDTO")
@Data
public class UserDTO implements Serializable
{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private String user_id;

    @NotEmpty(message = "error.message.name")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "error.message.birth_day")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "birth_day")
    private String birth_day;

    @NotEmpty(message = "error.message.sexo_fk")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "sexo_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexo_pk", referencedColumnName = "sexo_pk")
    private SexoDTO sexo_fk;

    @NotEmpty(message = "error.message.estado_civil_fk")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "estado_civil_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_civil_pk",referencedColumnName = "estado_civil_pk")
    private EstadoCivilDTO estado_civil_fk;

    @NotEmpty(message = "error.message.endereco_fk")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "endereco_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_pk",referencedColumnName = "endereco_pk")
    private EnderecoDTO endereco_fk;

    @NotEmpty(message = "error.message.email")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "error.message.phone")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "error.message.role_fk")
    @Length(max = 50, message = "error.message.length")
    @Column(name = "role_fk")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_pk", referencedColumnName = "role_pk")
    private String role_fk;
    
    

}
