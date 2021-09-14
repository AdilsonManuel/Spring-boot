/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.repository;

import com.azm.BuildRedditClone.dto.CommentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author azm
 */
@Repository
public interface CommentRepository extends MongoRepository<CommentDTO, String>
{
    
}
