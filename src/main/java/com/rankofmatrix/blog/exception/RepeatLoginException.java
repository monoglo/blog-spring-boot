package com.rankofmatrix.blog.exception;

public class RepeatLoginException extends RuntimeException{
    public RepeatLoginException() {super();}
    public RepeatLoginException(String message) {super(message);}
}
