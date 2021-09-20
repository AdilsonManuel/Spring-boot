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
@Table(name = "ProvinciaDTO")
@Data
public class ProvinciaDTO implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @NotEmpty(message = "error.message.name")
    @Length(max = 100, message = "error.message.length")
    @Column(name = "name")
    private String name;

//    @NotEmpty(message = "error.message.name")
//    @Length( max = 100, message = "error.message.length")
//    @Column(name = "pais_fk")
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "pais_pk", referencedColumnName = "pais_pk")
//    private Collection<PaisDTO> pais_fk = new ArrayList<>();
    @NotEmpty(message = "error.message.pais_fk")
    @Length(max = 100, message = "error.message.length")
    @Column(name = "id")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private PaisDTO pais_fk ;

}
