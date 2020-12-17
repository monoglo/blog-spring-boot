package com.rankofmatrix.blog.exception;

public class TagAndArticleAlreadyExistException extends RuntimeException{
    public TagAndArticleAlreadyExistException() {
        super();
    }
    public TagAndArticleAlreadyExistException(String message) {
        super(message);
    }
}
