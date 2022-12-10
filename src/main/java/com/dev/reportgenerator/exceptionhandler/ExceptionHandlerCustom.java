package com.dev.reportgenerator.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.text.ParseException;

@RestControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(true));

        apiError.setMessage("Required request parameter '" + ex.getParameterName() + "' for method parameter type String is not present ");
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomExceptionNotFound.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleCustomExceptionNotFound(CustomExceptionNotFound ex, WebRequest request) {
        ApiError errorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleAllException(ParseException ex, WebRequest request) {
        ApiError errorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());

    }

}
