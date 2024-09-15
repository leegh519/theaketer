package com.chbb.theaketing.feature.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected String handleTypeMismatchException() {
        return "error!";
    }

    @ExceptionHandler(TheaketingException.class)
    protected ResponseEntity<ErrorResponse> handleTheaketingException(
            TheaketingException e,
            HttpServletRequest request,
            HttpServletResponse response) {

        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getError()),
                HttpStatus.valueOf(e.getError().getStatus()));
    }
}
