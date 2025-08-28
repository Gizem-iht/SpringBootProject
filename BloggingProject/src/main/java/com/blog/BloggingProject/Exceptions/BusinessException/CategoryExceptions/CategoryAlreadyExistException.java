package com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class CategoryAlreadyExistException extends BusinessException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
