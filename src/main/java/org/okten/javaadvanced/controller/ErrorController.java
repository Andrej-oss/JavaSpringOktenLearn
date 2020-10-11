package org.okten.javaadvanced.controller;


import org.okten.javaadvanced.exceptions.CapitalLetterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {


Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = "Object" + fieldError.getObjectName()
                + ", Field: " + fieldError.getField() + " - "
                + fieldError.getDefaultMessage();
        logger.warn("Handling MethodArgumentNotValidException" + message);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "invalid input data", message);
    }
    @ExceptionHandler(value = {CapitalLetterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCapitalLetterException(CapitalLetterException e){
        logger.warn("CapitalLetterException" + e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "input capital letter error", e.getMessage());
    }
}
