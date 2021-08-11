/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author azm
 */
@Data
public class FieldValidationErrorDetails implements Serializable
{
    private String error_title;
    private String error_detail;
    private String error_path;
    private String error_developer_message;
    private int error_status;
    private long error_timeStamp;
    private Map<String,List<FieldValidationError>> errors = new HashMap<String, List<FieldValidationError>>();

    public FieldValidationErrorDetails (String error_title, String error_detail, String error_path, String error_developer_message, int error_status, long error_timeStamp)
    {
        this.error_title = error_title;
        this.error_detail = error_detail;
        this.error_path = error_path;
        this.error_developer_message = error_developer_message;
        this.error_status = error_status;
        this.error_timeStamp = error_timeStamp;
    }

    public FieldValidationErrorDetails ()
    {
    }
    
    
}
