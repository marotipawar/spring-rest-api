package com.maroti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomisedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
       ErrorDetails errorDetails=ErrorDetails.builder().localDate(LocalDate.now()).message(ex.getMessage()).description(request.getDescription(false)).build();
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
