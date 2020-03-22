package com.kach.studyhelperback.dto;

public abstract class ApiResponseTemplate {
    private Integer status;
    private Boolean success;

    public ApiResponseTemplate(Integer status, Boolean success) {
        this.status = status;
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
