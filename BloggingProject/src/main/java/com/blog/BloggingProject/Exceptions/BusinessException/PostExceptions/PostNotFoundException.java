package com.blog.BloggingProject.Exceptions.BusinessException.PostExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException(String message) {

        super(message);
    }
}
