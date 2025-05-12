package com.example.webdev_intern.exception.handler;

import com.example.webdev_intern.exception.BaseApiException;
import com.example.webdev_intern.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseObject.builder()
                        .message("System Error: " + ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .data(null)
                        .build()
        );
    }

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<?> handleBaseApiException(BaseApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ResponseObject.builder()
                        .status(ex.getStatus().value())
                        .message(ex.getMessage())
                        .build());
    }
}
