package com.kach.studyhelperback.service;

import java.util.Date;
import java.util.HashMap;

public interface ArticleStatService {
    /**
     * Получение количества просмотров статьи
     * @param articleId ID статьи
     * @return Количество просмотров
     */
    Long getArticleViews(Long articleId);

    /**
     * Получение общего количества просмотров для всех статей текущего автора за конкретную дату
     * @param date Дата просмотров
     * @return Количество просмотров
     */
    Long getArticlesStatByDate (Date date);

    /**
     * Получение количества просмотров для всех статей текущего автора по датам
     * @return Количество просмотров по датам
     */
    HashMap<String, Long> getArticlesStat();
}
