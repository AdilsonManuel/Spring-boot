/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.controller;

import com.apress.azm.EnterpriseResourcePlanning.repository.PaisRepository;
import com.apress.azm.EnterpriseResourcePlanning.util.ConstantAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.azm.EnterpriseResourcePlanning.dto.PaisDTO;
import com.apress.azm.EnterpriseResourcePlanning.exception.CustomErrorType;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class PaisController
{

    public static final Logger LOGGER = LoggerFactory.getLogger (PaisController.class);

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping(ConstantAPI.COUNTRY + "/")
    public ResponseEntity<List< PaisDTO>> listAll ()
    {
        List<PaisDTO> paisDTOs = paisRepository.findAll ();

        if (paisDTOs.isEmpty ())
        {
            return new ResponseEntity<List<PaisDTO>> (HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PaisDTO>> (paisDTOs, HttpStatus.OK);

    }

    @PostMapping(value = ConstantAPI.COUNTRY + "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisDTO> createCrounrty (@Valid @RequestBody final PaisDTO paisDTO)
    {
        LOGGER.info ("Creating country: {}", paisDTO);
        if (paisRepository.findByName (paisDTO.getName ()) != null)
        {
            LOGGER.error ("Unable to create. A Country with name {} already exist", paisDTO.getName ());
            return new ResponseEntity<PaisDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to create new country. A country with name " + paisDTO.getName () + " already exist."), HttpStatus.CONFLICT);
        }
        paisRepository.save (paisDTO);
        return new ResponseEntity<PaisDTO> (paisDTO, HttpStatus.CREATED);
    }

    @GetMapping(ConstantAPI.COUNTRY + "/{id}")
    public ResponseEntity<PaisDTO> getUserByID (@PathVariable("id") final String id)
    {
        PaisDTO paisDTO = paisRepository.findByID (id);

        if (paisDTO == null)
        {
            return new ResponseEntity<PaisDTO> ((MultiValueMap<String, String>) new CustomErrorType ("A Country with id" + id + "not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PaisDTO> (paisDTO, HttpStatus.OK);
    }

    @PutMapping(value = ConstantAPI.COUNTRY + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisDTO> updateCountry (@PathVariable("id") final String id, @RequestBody PaisDTO paisDTO)
    {
        PaisDTO currentPaisDTO = paisRepository.findByID (id);

        if (paisDTO == null)
        {
            return new ResponseEntity<PaisDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to update. A Country with id" + id + "not found"), HttpStatus.NOT_FOUND);
        }

        currentPaisDTO.setName (paisDTO.getName ());

        paisRepository.save (currentPaisDTO);

        return new ResponseEntity<PaisDTO> (currentPaisDTO, HttpStatus.OK);
    }

    @DeleteMapping(ConstantAPI.COUNTRY + "/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteCountry (@PathVariable("id") final String id)
    {
        PaisDTO paisDTO = paisRepository.findByID (id);

        if (paisDTO == null)
        {
            return new ResponseEntity<PaisDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to delete. A Country with id" + id + "not found"), HttpStatus.NOT_FOUND);
        }
        paisRepository.deleteById (id);

        return new ResponseEntity<PaisDTO> (HttpStatus.NO_CONTENT);
    }

}
