package com.kach.studyhelperback.service;

import com.kach.studyhelperback.dto.helper.ArticleLogMin;
import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleLog;
import com.kach.studyhelperback.model.User;

import java.util.Date;
import java.util.List;

public interface LogService {
    /**
     * Добавление лога об обращении к статье
     * @param article
     */
    void log(Article article);

    /**
     * Получение всех логов
     * @return Список логов
     */
    List<ArticleLog> getLogs();

    /**
     * Получение логов по одной статье
     * @param article Объект статьи
     * @return Список логов
     */
    List<ArticleLog> getLogs(Article article);

    /**
     * Получение логов по одной статье
     * @param articleId ID статьи
     * @return Список логов
     */
    List<ArticleLog> getLogs(Long articleId);

    /**
     * Получение логов по всем статьям за определённую дату
     * @param date Дата
     * @return Список логов
     */
    List<ArticleLog> getLogs(Date date);

    /**
     * Получение логов посещения статей конкретным пользователем
     * @param user Объект пользователя
     * @return Список логов
     */
    List<ArticleLog> getLogs(User user);
    List<ArticleLogMin> getMinLogs();
}
