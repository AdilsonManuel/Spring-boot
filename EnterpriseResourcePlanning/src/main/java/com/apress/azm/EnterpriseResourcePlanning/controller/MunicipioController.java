/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.controller;

import com.apress.azm.EnterpriseResourcePlanning.dto.MunicipioDTO;
import com.apress.azm.EnterpriseResourcePlanning.exception.CustomErrorType;
import com.apress.azm.EnterpriseResourcePlanning.util.ConstantAPI;
import com.apress.azm.EnterpriseResourcePlanning.repository.MunicipioRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author azm
 */
@RestController
@RequestMapping(ConstantAPI.API)
public class MunicipioController
{

    private static final Logger LOGGER = LoggerFactory.getLogger (MunicipioController.class);

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping(ConstantAPI.CITY + "/")
    public ResponseEntity<List<MunicipioDTO>> listAll ()
    {
        List<MunicipioDTO> municipioDTOs = municipioRepository.findAll ();

        if (municipioDTOs.isEmpty ())
        {
            return new ResponseEntity<List<MunicipioDTO>> (HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<MunicipioDTO>> (municipioDTOs, HttpStatus.OK);

    }

    @PostMapping(value = ConstantAPI.CITY + "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MunicipioDTO> createCity (@Valid @RequestBody MunicipioDTO municipioDTO)
    {
        LOGGER.info ("Creating City {} " + municipioDTO.getName ());

        if (municipioDTO == null)
        {
            LOGGER.error ("Unable to create. A City with name{} already exist", municipioDTO.getName ());
            return new ResponseEntity<MunicipioDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to create new City. A City with name " + municipioDTO.getName () + " already exist."), HttpStatus.CONFLICT);
        }

        municipioRepository.save (municipioDTO);

        return new ResponseEntity<MunicipioDTO> (municipioDTO, HttpStatus.CREATED);
    }

    @GetMapping(ConstantAPI.CITY + "/{id}")
    public ResponseEntity<MunicipioDTO> getCityByid (@PathVariable("id") final String id)
    {
        MunicipioDTO municipioDTO = municipioRepository.findByID (id);

        if (municipioDTO == null)
        {
            return new ResponseEntity<MunicipioDTO> ((MultiValueMap<String, String>) new CustomErrorType ("City with id" + id + "Not found"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<MunicipioDTO> (municipioDTO, HttpStatus.OK);

    }

    @PutMapping(value = ConstantAPI.CITY + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MunicipioDTO> updateCity (@PathVariable("id") final String id, @Valid @RequestBody MunicipioDTO municipioDTO)
    {
        MunicipioDTO currentMunicipioDTO = municipioRepository.findByID (id);

        if (currentMunicipioDTO == null)
        {
            return new ResponseEntity<MunicipioDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to update. City with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }

        currentMunicipioDTO.setName (municipioDTO.getName ());
//        currentMunicipioDTO.setProvincia_fk (municipioDTO.getProvincia_fk ());

        municipioRepository.save (currentMunicipioDTO);

        return new ResponseEntity<MunicipioDTO> (currentMunicipioDTO, HttpStatus.OK);
    }

    @DeleteMapping(ConstantAPI.CITY + "/{id}")
    public ResponseEntity<MunicipioDTO> deleteCity (@PathVariable("id") final String id)
    {
        MunicipioDTO municipioDTO = municipioRepository.findByID (id);

        if (municipioDTO == null)
        {
            return new ResponseEntity<MunicipioDTO> ((MultiValueMap<String, String>) new CustomErrorType ("Unable to delete. City with id" + id + "not found."), HttpStatus.NOT_FOUND);
        }

        municipioRepository.delete (municipioDTO);

        return new ResponseEntity<MunicipioDTO> (HttpStatus.NO_CONTENT);
    }
}
