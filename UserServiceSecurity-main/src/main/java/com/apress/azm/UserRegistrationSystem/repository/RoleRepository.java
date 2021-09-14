/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.repository;

import com.apress.azm.UserRegistrationSystem.DTO.RoleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface RoleRepository extends MongoRepository<RoleDTO, String>
{

    @Query(value = "{'name' : ?0}", fields = "{'name': 1, _id : 0}")
    RoleDTO findByName (String username);
}
