package com.blog.BloggingProject.Exception.PostException;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {

        super(message);
    }
}
