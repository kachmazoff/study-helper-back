package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;

import java.util.List;

public interface ArticleService {
    /**
     * Получение статьи
     * @param id ID статьи
     * @return Объект Article искомой статьи
     */
    Article getArticle(Long id);

    /**
     * Получение статьи и создание лога об обращении к статье
     * @param id ID статьи
     * @return Объект Article искомой статьи
     */
    Article getFullArticle(Long id);

    /**
     * Получение новых статей
     * @return Список из не более чем 10 последних созданных статей
     */
    List<Article> getNewArticles();

    /**
     * Получение всех статей
     * @return Список статей
     */
    List<Article> getAllArticles();

    /**
     * Получение всех статей определённого типа
     * @param type Требуемый тип статей
     * @return Список статей
     */
    List<Article> getAllArticles(ArticleType type);

    /**
     * Получение статей, созданных текущим пользователем
     * @return Список статей
     */
    List<Article> getAllMyArticles();

    /**
     * Получение последних просмотренных статей
     * @return Список статей
     */
    List<Article> getLastViewedArticles();

    /**
     * Создание новой статьи
     * @param article Обьект Article новой статьи
     */
    void addArticle(Article article);

    /**
     * Обновление существующей статьи
     * @param articleId ID статьи, требующей изменение
     * @param article Обновленный объект Article
     */
    void updateArticle(Long articleId, Article article);

    /**
     * Удаление существующей статьи
     * @param articleId ID удаляемой статьи
     */
    void deleteArticle(Long articleId);

    /**
     * Установка флага isPublished для статьи
     * @param articleId ID статьи, для которой нужно изменить флаг
     * @param isPublished Новое значение флага
     */
    void setArticlePublished(Long articleId, Boolean isPublished);
}
