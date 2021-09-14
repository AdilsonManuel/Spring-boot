/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.repository;

import com.apress.azm.UserRegistrationSystem.DTO.RoleDTO;
import com.apress.azm.UserRegistrationSystem.DTO.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface UserRepository extends MongoRepository<UserDTO, String>
{

    @Query(value = "{'userName' : ?0}", fields = "{'userName': 1, _id : 1,'password' : 1, 'name': 1, 'roles':1}")
    UserDTO findByName (String username);
}
