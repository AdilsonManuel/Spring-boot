/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.controller;

import com.apress.azm.EnterpriseResourcePlanning.dto.PaisDTO;
import com.apress.azm.EnterpriseResourcePlanning.dto.ProvinciaDTO;
import com.apress.azm.EnterpriseResourcePlanning.exception.CustomErrorType;
import com.apress.azm.EnterpriseResourcePlanning.util.ConstantAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.azm.EnterpriseResourcePlanning.repository.ProvinciaRepository;
import com.apress.azm.EnterpriseResourcePlanning.repository.PaisRepository;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping(ConstantAPI.API)
public class ProvinciaController
{

    public static final Logger logger = LoggerFactory.getLogger (ProvinciaController.class);

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping(ConstantAPI.PROVINCE + "/")
    public ResponseEntity<List<ProvinciaDTO>> listAll ()
    {
        List<ProvinciaDTO> provinciaDTOs = provinciaRepository.findAll ();

        if (provinciaDTOs.isEmpty ())
        {
            return new ResponseEntity<List<ProvinciaDTO>> (HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProvinciaDTO>> (provinciaDTOs, HttpStatus.OK);
    }

    @PostMapping(value = ConstantAPI.PROVINCE + "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProvinciaDTO> createPronvice (@Valid @RequestBody final ProvinciaDTO provinciaDTO)
    {
        logger.info ("Creating Province {}", provinciaDTO);

        if (provinciaRepository.findByName (provinciaDTO.getName ()) != null)
        {
            logger.error ("Unable to create. A Province with name{} already exist", provinciaDTO.getName ());
            return new ResponseEntity<ProvinciaDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to create new Province. A Province with name " + provinciaDTO.getName () + " already exist."), HttpStatus.CONFLICT);

        }
        provinciaRepository.save (provinciaDTO);
        return new ResponseEntity<ProvinciaDTO> (provinciaDTO, HttpStatus.CREATED);
    }

    @GetMapping(ConstantAPI.PROVINCE + "{/{id}")
    public ResponseEntity<ProvinciaDTO> getProvinceById (@PathVariable("{id}") String id)
    {
        ProvinciaDTO provinciaDTO = provinciaRepository.findByID (id);

        if (provinciaDTO == null)
        {
            return new ResponseEntity<ProvinciaDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Province with id" + id + "Not found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<ProvinciaDTO> (provinciaDTO, HttpStatus.OK);
    }

    @PutMapping(value = ConstantAPI.PROVINCE + "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProvinciaDTO> updateProvince (@PathVariable("{id}") final String id, @RequestBody ProvinciaDTO provinciaDTO)
    {
        ProvinciaDTO currentProvinciaDTO = provinciaRepository.findByID (id);

        if (currentProvinciaDTO == null)
        {
            return new ResponseEntity<ProvinciaDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to update. Province with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }

        currentProvinciaDTO.setName (provinciaDTO.getName ());
//        currentProvinciaDTO.setPais_fk ();

        provinciaRepository.save (currentProvinciaDTO);
        
        return new ResponseEntity<ProvinciaDTO> (currentProvinciaDTO, HttpStatus.OK);
    }

    @DeleteMapping(ConstantAPI.PROVINCE + "/{id}")
    public ResponseEntity<ProvinciaDTO> deleteProvince (@PathVariable("id") final String id)
    {
        ProvinciaDTO provinciaDTO = provinciaRepository.findByID (id);

        if (provinciaDTO == null)
        {
            return new ResponseEntity<ProvinciaDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to detele. Province with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }

        provinciaRepository.delete (provinciaDTO);

        return new ResponseEntity<ProvinciaDTO> (HttpStatus.NO_CONTENT);
    }

//    @PostMapping(value = ConstantAPI.PROVINCE + "/addCountry", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void addProvinceToCountry (final String countryName, final String provinceName)
//    {
//        logger.info ("Adding Province {} to Country {}", provinceName, countryName);
//
//        PaisDTO paisDTO = paisRepository.findByName (countryName);
//        ProvinciaDTO provinciaDTO = provinciaRepository.findByName (provinceName);
//
//        provinciaDTO.getPais_fk ().add (paisDTO);
//    }
}
