/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.apress.azm.EnterpriseResourcePlanning.dto.ProvinciaDTO;
import org.springframework.data.mongodb.repository.Query;
/**
 *
 * @author azm
 */
@Repository
public interface ProvinciaRepository extends MongoRepository<ProvinciaDTO, String>
{

    @Query(value = "{'name' : ?0}", fields = "{'name' : 1, _id : 0}")
    ProvinciaDTO findByName (String name);

    @Query(value = "{'_id' : ?0}")
    ProvinciaDTO findByID (String id);
    
}
