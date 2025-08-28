package com.blog.BloggingProject.Exceptions.BusinessException.UserExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class EmailAlreadyExistException extends BusinessException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
