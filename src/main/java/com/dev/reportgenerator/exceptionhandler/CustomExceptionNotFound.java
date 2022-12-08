package com.dev.reportgenerator.exceptionhandler;

public class CustomExceptionNotFound extends Exception{
    private static final long serialVersionUID = 1L;

    public CustomExceptionNotFound(String message){
        super(message);
    }
}
