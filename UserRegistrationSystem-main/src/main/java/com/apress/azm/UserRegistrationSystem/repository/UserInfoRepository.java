/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.repository;

import com.apress.azm.UserRegistrationSystem.dto.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String>
{

    @Query(value = "{'userName' : ?0}", fields = "{'userName': 1,'password': 1, 'enabled': 1,'role': 1} ")
    UserInfo findByName (String name);
}
