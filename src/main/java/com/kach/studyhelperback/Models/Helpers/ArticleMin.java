package com.kach.studyhelperback.Models.Helpers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kach.studyhelperback.Models.DAO.ArticleType;

public interface ArticleMin {
    Integer getId();
    String getHeader();
    ArticleType getType();
//    public Boolean getIsPublished() {
//        return isPublished;
//    }
}
