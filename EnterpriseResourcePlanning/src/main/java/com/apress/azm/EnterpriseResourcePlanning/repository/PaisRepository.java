/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.apress.azm.EnterpriseResourcePlanning.dto.PaisDTO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface PaisRepository extends MongoRepository<PaisDTO, String>
{

    @Query(value = "{'name' : ?0}", fields = "{'name' : 1, _id : 0 }")
    PaisDTO findByName (String name);

    @Query(value = "{'_id': ?0}")
    PaisDTO findByID (String id);

}
