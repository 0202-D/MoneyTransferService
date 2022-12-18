package ru.netology.dmitriypetrov.moneytransferservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static AtomicInteger exceptionId = new AtomicInteger(0);

    @ExceptionHandler
    public ResponseEntity<AppException> handleExceptionInput(ErrorInputException e) {
        AppException exception = new AppException();
        exception.setDescription(e.getMessage());
        exceptionId.addAndGet(1);
        exception.setId(exceptionId.get());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<AppException> handleExceptionData(ErrorTransferException e){
        AppException exception = new AppException();
        exception.setDescription(e.getMessage());
        exceptionId.addAndGet(1);
        exception.setId(exceptionId.get());
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<AppException> paramExceptionHandler(MethodArgumentNotValidException e) {
        AppException exception = new AppException();
        exception.setDescription(e.getMessage());
        exceptionId.addAndGet(1);
        exception.setId(exceptionId.get());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);

    }

    }
