package com.kach.studyhelperback.dto;

public class ApiResponseSuccess<T> extends ApiResponseTemplate {
    private T data;

    public ApiResponseSuccess(T data) {
        super(200, true);
        this.data = data;
    }

    public ApiResponseSuccess(T data, Integer status) {
        super(status, true);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}