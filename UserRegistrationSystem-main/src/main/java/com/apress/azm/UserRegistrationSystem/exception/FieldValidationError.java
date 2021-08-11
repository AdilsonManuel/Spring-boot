/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author azm
 */
@Getter
@Setter
public class FieldValidationError
{
    private String field;
    private String message;
    private MessageType messageType;
    
    public enum MessageType{
        SUCCESS, INFO, WARNING, ERROR
    }
}
