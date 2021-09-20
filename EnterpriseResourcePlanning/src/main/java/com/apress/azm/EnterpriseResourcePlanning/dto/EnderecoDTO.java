/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.dto;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "EnderecoDTO")
@Data
public class EnderecoDTO implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_pk")
    private String endereco_pk;

    @NotEmpty(message = "error.message.municipio_fk")
    @Length(max = 300, message = "error.message.length")
    @Column(name = "id")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<MunicipioDTO> municipio_fk;

    @NotEmpty(message = "error.message.rua")
    @Length(max = 100, message = "error.message.length")
    @Column(name = "rua")
    private String rua;

    @NotEmpty(message = "error.message.numero_casa")
    @Length(max = 50, message = "error.message.length")
    @Column(name = "numero_casa")
    private String numero_casa;

    @NotEmpty(message = "error.message.bairro")
    @Length(max = 60, message = "error.message.length")
    @Column(name = "bairro")
    private String bairro;

}
