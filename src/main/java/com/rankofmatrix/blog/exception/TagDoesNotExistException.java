package com.rankofmatrix.blog.exception;

public class TagDoesNotExistException extends RuntimeException {

    public TagDoesNotExistException() {
        super();
    }

    public TagDoesNotExistException(String message) {
        super(message);
    }

}