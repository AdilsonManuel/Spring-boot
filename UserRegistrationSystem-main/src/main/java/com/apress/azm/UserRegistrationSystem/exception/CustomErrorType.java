/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.exception;

import com.apress.azm.UserRegistrationSystem.dto.UserDTO;

/**
 *
 * @author azm
 */
public class CustomErrorType extends UserDTO
{

    private String errorMessage;

    public CustomErrorType (final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage ()
    {
        return errorMessage;
    }

}
