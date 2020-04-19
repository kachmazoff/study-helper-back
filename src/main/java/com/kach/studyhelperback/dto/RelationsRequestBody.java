package com.kach.studyhelperback.dto;

public class RelationsRequestBody {
    private Long curId;
    private Long fromId;

    public Long getCurId() {
        return curId;
    }

    public void setCurId(Long curId) {
        this.curId = curId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }
}
