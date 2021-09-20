/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import com.apress.azm.EnterpriseResourcePlanning.dto.MunicipioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface MunicipioRepository extends MongoRepository<MunicipioDTO, String>
{

    @Query("{'_id' : ?0}")
    MunicipioDTO findByID (String id);
    
}
