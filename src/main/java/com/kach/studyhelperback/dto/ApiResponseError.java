package com.kach.studyhelperback.dto;

public class ApiResponseError extends ApiResponseTemplate {
    private String message;

    public ApiResponseError(Integer status, String message) {
        super(status, false);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
