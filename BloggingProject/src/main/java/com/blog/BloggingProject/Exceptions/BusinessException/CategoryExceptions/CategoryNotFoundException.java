package com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions;

import com.blog.BloggingProject.Exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message) {

        super(message);
    }
}
