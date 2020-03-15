package com.kach.studyhelperback.Services.Definitions;

import com.kach.studyhelperback.Models.DAO.ArticleType;

import java.util.List;

public interface ArticleTypesService {
    List<ArticleType> getAllTypes();
    ArticleType getType(String typeName);
    void addType(ArticleType type);
    void deleteType(Integer typeId);
    void updateType(Integer typeId, ArticleType updatedType);
}
