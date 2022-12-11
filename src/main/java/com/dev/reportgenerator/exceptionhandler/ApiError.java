package com.dev.reportgenerator.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private String decription;

//    private ApiError() {
//        timestamp = LocalDateTime.now();
//    }

    public ApiError(HttpStatus status) {
        this.status = status;
    }


    public ApiError(HttpStatus status,  String message, String decription) {

        this.status = status;
        this.message = message;
        this.decription = decription;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }


}
