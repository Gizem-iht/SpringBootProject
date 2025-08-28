package com.blog.BloggingProject.Exceptions.BusinessException.CommentExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class CommentNotFoundException extends BusinessException {
    public CommentNotFoundException(String message) {

        super(message);
    }
}