package com.imrzoom.desafioanotaai.exceptions.handler;

import com.imrzoom.desafioanotaai.exceptions.CategoryNotFoundException;
import com.imrzoom.desafioanotaai.exceptions.ErrorMessage;
import com.imrzoom.desafioanotaai.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<ErrorMessage> categoryNotFoundException(
            CategoryNotFoundException ex, HttpServletRequest request){

        log.error("API Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorMessage> productNotFoundException(
            ProductNotFoundException ex, HttpServletRequest request){

        log.error("API Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

}
