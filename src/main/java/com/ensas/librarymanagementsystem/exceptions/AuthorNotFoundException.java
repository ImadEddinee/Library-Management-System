package com.ensas.librarymanagementsystem.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message){
        super(message);
    }

}
