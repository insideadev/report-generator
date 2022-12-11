package com.dev.reportgenerator.exceptionhandler;

import java.io.Serializable;

public class CustomExceptionNotFound extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public CustomExceptionNotFound(String message){
        super(message);
    }
}
