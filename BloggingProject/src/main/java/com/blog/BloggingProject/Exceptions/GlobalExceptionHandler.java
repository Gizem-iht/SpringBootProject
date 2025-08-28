package com.blog.BloggingProject.Exceptions;


import com.blog.BloggingProject.Core.Utilities.ErrorDataResult;
import com.blog.BloggingProject.Core.Utilities.ErrorResult;
import com.blog.BloggingProject.Exception.BusinessException;
import com.blog.BloggingProject.Exceptions.BusinessException.CategoryExceptions.CategoryAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessException(BusinessException businessException){

        ErrorDataResult<Object> error = new ErrorDataResult<>(businessException.getMessage(), businessException.getClass().getSimpleName()+".Error");

        return error;
    }


}

