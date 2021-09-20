/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.EnterpriseResourcePlanning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.apress.azm.EnterpriseResourcePlanning.dto.SexoDTO;
/**
 *
 * @author azm
 */
@Repository
public interface SexoRepository extends MongoRepository<SexoDTO, String>
{
    
}
