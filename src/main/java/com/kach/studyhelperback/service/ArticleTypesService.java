package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.ArticleType;

import java.util.List;

public interface ArticleTypesService {
    List<ArticleType> getAllTypes();
    ArticleType getType(String typeName);
    void addType(ArticleType type);
    void deleteType(Long typeId);
    void updateType(Long typeId, ArticleType updatedType);
}
