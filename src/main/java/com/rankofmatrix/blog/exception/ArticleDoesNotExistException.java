package com.rankofmatrix.blog.exception;

public class ArticleDoesNotExistException extends RuntimeException {

    public ArticleDoesNotExistException() {
        super();
    }

    public ArticleDoesNotExistException(String message) {
        super(message);
    }

}
