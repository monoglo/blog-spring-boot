package com.rankofmatrix.blog.exception;

public class ArticleIsHiddenException extends RuntimeException{

    public ArticleIsHiddenException() {
        super();
    }

    public ArticleIsHiddenException(String message) {
        super(message);
    }
}
