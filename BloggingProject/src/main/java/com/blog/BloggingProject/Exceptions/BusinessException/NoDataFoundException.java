package com.blog.BloggingProject.Exceptions.BusinessException;

import com.blog.BloggingProject.Exception.BusinessException;

public class NoDataFoundException extends BusinessException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
