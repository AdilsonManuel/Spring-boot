/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azm.BuildRedditClone.exception;

/**
 *
 * @author azm
 */
public class SpringRedditException extends RuntimeException
{

    public SpringRedditException (String messge)
    {
        super (messge);
    }
    
}
