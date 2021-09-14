/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.dto;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author azm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationEmailDTO
{
    private String subject;
    private String recipient;
    private String body;
}
