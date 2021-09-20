/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.apress.azm.EnterpriseResourcePlanning.dto.EstadoCivilDTO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface EstadoCivilRepository extends MongoRepository<EstadoCivilDTO, String>
{
    
}
