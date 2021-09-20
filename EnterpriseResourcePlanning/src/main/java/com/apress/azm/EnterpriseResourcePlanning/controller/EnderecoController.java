/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.controller;

import com.apress.azm.EnterpriseResourcePlanning.dto.EnderecoDTO;
import com.apress.azm.EnterpriseResourcePlanning.repository.EnderecoRepository;
import com.apress.azm.EnterpriseResourcePlanning.util.ConstantAPI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping(ConstantAPI.API)
public class EnderecoController
{

    private static final Logger LOGGER = LoggerFactory.getLogger (EnderecoController.class);

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping(ConstantAPI.ADDRESS + "{id}")
    public ResponseEntity<List<EnderecoDTO>> listAll ()
    {
        List<EnderecoDTO> enderecoDTOs = enderecoRepository.findAll ();

        if (enderecoDTOs.isEmpty ())
        {
            return new ResponseEntity<List<EnderecoDTO>> (HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<EnderecoDTO>> (enderecoDTOs, HttpStatus.OK);
    }
}
