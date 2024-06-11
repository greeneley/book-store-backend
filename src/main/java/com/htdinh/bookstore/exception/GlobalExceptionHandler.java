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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()).status(HttpStatus.BAD_REQUEST).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TokenInValidException.class)
    public ResponseEntity<ErrorResponse> handleTokenInValidException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.NOT_FOUND.value()).status(HttpStatus.NOT_FOUND).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnumException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.NOT_FOUND.value()).status(HttpStatus.NOT_FOUND).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.UNAUTHORIZED.value()).status(HttpStatus.UNAUTHORIZED).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(Exception e) {
        ErrorResponse error = ErrorResponse.builder().code(HttpStatus.FORBIDDEN.value()).status(HttpStatus.FORBIDDEN).message(e.getMessage()).timestamp(LocalDateTime.now()).build();
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
