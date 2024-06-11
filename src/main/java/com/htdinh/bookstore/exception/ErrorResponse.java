package com.htdinh.bookstore.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private int code;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
