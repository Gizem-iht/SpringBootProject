package com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {

        super(message);
    }
}
