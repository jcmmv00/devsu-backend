package com.devsu.devsu.application.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import java.text.ParseException;

import com.devsu.devsu.core.exceptions.ClientAlreadyExistException;
import com.devsu.devsu.core.exceptions.ClientNotFoundException;
import com.devsu.devsu.core.exceptions.DailyLimitExceeded;
import com.devsu.devsu.core.exceptions.MovementBalanceNotAvailableException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.devsu.devsu.core.exceptions.AccountNotFoundException;
import com.devsu.devsu.core.exceptions.AccountAlreadyExistsException;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ClientNotFoundException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ClientAlreadyExistException.class})
    public ResponseEntity<Object> handleAlreadyExistsException(ClientAlreadyExistException ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

     @ExceptionHandler({AccountAlreadyExistsException.class})
    public ResponseEntity<Object> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({DailyLimitExceeded.class})
    public ResponseEntity<Object> handleDailyLimitExceeded(DailyLimitExceeded ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({MovementBalanceNotAvailableException.class})
    public ResponseEntity<Object> handleMovementBalanceNotAvailableException(MovementBalanceNotAvailableException ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

       @ExceptionHandler({ParseException.class})
    public ResponseEntity<Object> handleMovementBalanceNotAvailableException(ParseException ex,
            final WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}