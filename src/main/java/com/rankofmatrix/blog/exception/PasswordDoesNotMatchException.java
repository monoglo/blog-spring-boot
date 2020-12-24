package com.rankofmatrix.blog.exception;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException() {super();}
    public PasswordDoesNotMatchException(String message) {super(message);}
}
