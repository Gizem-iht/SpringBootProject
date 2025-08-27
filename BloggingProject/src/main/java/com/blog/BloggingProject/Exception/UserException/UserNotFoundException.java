package com.blog.BloggingProject.Exception.UserException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {

        super(message);
    }
}
