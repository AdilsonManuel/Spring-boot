/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.azm.UserRegistrationSystem.exception;

import com.apress.azm.UserRegistrationSystem.exception.FieldValidationError.MessageType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author azm
 */
@ControllerAdvice
public class RestValidationHandler
{

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public ResponseEntity<FieldValidationErrorDetails> handleValidationError (MethodArgumentNotValidException notValidException, HttpServletRequest request)
    {
        FieldValidationErrorDetails fErrorDetails = new FieldValidationErrorDetails ();
        fErrorDetails.setError_timeStamp (new Date ().getTime ());
        fErrorDetails.setError_status (HttpStatus.BAD_REQUEST.value ());
        fErrorDetails.setError_title ("Field Validation Error");
        fErrorDetails.setError_detail ("Input Fiel Validation failed");
        fErrorDetails.setError_developer_message (notValidException.getClass ().getName ());
        fErrorDetails.setError_path (request.getRequestURI ());

        BindingResult result = notValidException.getBindingResult ();
        List<FieldError> fieldErrors = result.getFieldErrors ();

        for (FieldError fieldError : fieldErrors)
        {
            FieldValidationError fError = processFieldError (fieldError);
            List<FieldValidationError> fvlaValidationErrors = fErrorDetails.getErrors ().get (fError.getField ());

            if (fvlaValidationErrors == null)
            {
                fvlaValidationErrors = new ArrayList<FieldValidationError> ();
            }

            fvlaValidationErrors.add (fError);
            fErrorDetails.getErrors ().put (fError.getField (), fvlaValidationErrors);
        }
        return new ResponseEntity<FieldValidationErrorDetails> (fErrorDetails, HttpStatus.BAD_REQUEST);
    }

    private FieldValidationError processFieldError (FieldError fieldError)
    {
        FieldValidationError fieldValidationError = new FieldValidationError ();

        if (fieldError != null)
        {
            Locale currentLocale = LocaleContextHolder.getLocale ();
            String msg = messageSource.getMessage (fieldError.getDefaultMessage (), null, currentLocale);

            fieldValidationError.setField (fieldError.getField ());
            fieldValidationError.setMessageType (MessageType.ERROR);
            fieldValidationError.setMessage (msg);
        }
        return fieldValidationError;
    }
}
