package com.htdinh.bookstore.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // ResourceNotFoundException now returns 404 NOT_FOUND instead of 400 BAD_REQUEST
    // TokenInValidException now returns 401 UNAUTHORIZED instead of 404 NOT_FOUND
    // InvalidEnumException now returns 400 BAD_REQUEST instead of 404 NOT_FOUND
    // ExpiredJwtException now returns 401 UNAUTHORIZED instead of 403 FORBIDDEN
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, Exception e) {
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .status(status)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.error("Exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(TokenInValidException.class)
    public ResponseEntity<ErrorResponse> handleTokenInValidException(TokenInValidException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnumException(InvalidEnumException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
}
