package com.blog.BloggingProject.Exception.CommentException;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {

        super(message);
    }
}