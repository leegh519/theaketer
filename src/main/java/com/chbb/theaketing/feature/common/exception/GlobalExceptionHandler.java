package com.chbb.theaketing.feature.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleTypeMismatchException(
            BindException e,
            HttpServletRequest request,
            HttpServletResponse response) {
        ErrorResponse err = ErrorResponse.builder()
                .message(e.getFieldError().getDefaultMessage())
                .code("C000")
                .build();
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.valueOf(err.getStatus()));
    }

    @ExceptionHandler(TheaketingException.class)
    protected ResponseEntity<ErrorResponse> handleTheaketingException(
            TheaketingException e,
            HttpServletRequest request,
            HttpServletResponse response) {

        return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getError()),
                HttpStatus.valueOf(e.getError().getStatus()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(
            BadCredentialsException e,
            HttpServletRequest request,
            HttpServletResponse response) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorCode.AUTHENTICATION_FAIL),
                HttpStatus.valueOf(400));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleUnknownException(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response) {

        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorCode.UNKNOWN),
                HttpStatus.valueOf(ErrorCode.UNKNOWN.getStatus()));
    }
}
