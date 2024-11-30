package tech.lastbox.controller;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.lastbox.dtos.ErrorResponse;
import tech.lastbox.exceptions.task.InvalidStatusException;

@ControllerAdvice
@Order(0)
public class ErrorAdviceController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException ex) {
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
                );
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorResponse> invalidStatusExceptionHandler(InvalidStatusException ex) {
        return ResponseEntity.status(
                        HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY)
                );
    }
}
