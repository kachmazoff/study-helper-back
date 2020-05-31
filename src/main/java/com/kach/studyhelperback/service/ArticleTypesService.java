package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.ArticleType;

import java.util.List;

public interface ArticleTypesService {
    /**
     * Получение всех типов статей
     * @return Список типов
     */
    List<ArticleType> getAllTypes();

    /**
     * Получение объекта типа статьи
     * @param typeName Название типа
     * @return Объект типа статьи
     */
    ArticleType getType(String typeName);

    /**
     * Получение объекта типа статьи по его ID
     * @param id ID статьи
     * @return Объект статьи
     */
    ArticleType getType(Long id);

    /**
     * Создание типа статьи
     * @param type Объект нового типа
     */
    void addType(ArticleType type);

    /**
     * Удаление типа статьи
     * @param typeId ID удаляемой статьи
     */
    void deleteType(Long typeId);

    /**
     * Обновление типа статьи
     * @param typeId ID сузествующего типа
     * @param updatedType Обновлённый обект типа
     */
    void updateType(Long typeId, ArticleType updatedType);
}
