/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import com.apress.azm.EnterpriseResourcePlanning.dto.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author azm
 */
public interface CustomerRepository extends MongoRepository<CustomerDTO, String>
{
    
}
