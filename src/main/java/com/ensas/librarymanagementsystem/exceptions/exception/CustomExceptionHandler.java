package com.ensas.librarymanagementsystem.exceptions.exception;

import com.ensas.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.ensas.librarymanagementsystem.exceptions.BookNotFoundException;
import com.ensas.librarymanagementsystem.exceptions.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductException(BookNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),new Date(),badRequest
        );
        return new ResponseEntity<>(exceptionResponse, badRequest);
    }

    @ExceptionHandler(value = AuthorNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductException(AuthorNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),new Date(),badRequest
        );
        return new ResponseEntity<>(exceptionResponse, badRequest);
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductException(CategoryNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),new Date(),badRequest
        );
        return new ResponseEntity<>(exceptionResponse, badRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getFieldError().getDefaultMessage(), new Date(),httpStatus
        );
        return new ResponseEntity<>(exceptionResponse,httpStatus);
    }
}
