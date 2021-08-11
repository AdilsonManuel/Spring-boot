/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.repository;

import com.apress.azm.UserRegistrationSystem.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author azm
 */
public interface UserRepository extends MongoRepository<UserDTO, String>
{

    @Query(value = "{'name' : ?0}", fields = "{'name': 1, _id : 0}")
    UserDTO findByName (String name);

    @Query(value = "{'_id' : ?0}")
    UserDTO findByID (String id);
}
