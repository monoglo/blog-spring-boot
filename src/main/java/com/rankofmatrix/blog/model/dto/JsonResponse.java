package com.rankofmatrix.blog.model.dto;

public class JsonResponse {
    private Integer code;
    private String message;
    private Integer length;
    private Object data;

    public JsonResponse(Integer code, String message, Integer length, Object data) {
        this.code = code;
        this.message = message;
        this.length = length;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}