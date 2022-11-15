package com.rioc.ws.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandler {
    @ExceptionHandler(value={ApiException.class})
    public ResponseEntity<Object> handleException(ApiException apiException){
        ExceptionMessage exceptionMessage = new ExceptionMessage(apiException.getMessage());
        return new ResponseEntity<>(exceptionMessage, apiException.getHttpStatus());
    }
}
