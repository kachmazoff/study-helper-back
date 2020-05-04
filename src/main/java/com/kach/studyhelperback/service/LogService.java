package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleLog;
import com.kach.studyhelperback.model.User;

import java.util.Date;
import java.util.List;

public interface LogService {
    void log(Article article);
    List<ArticleLog> getLogs();
    List<ArticleLog> getLogs(Article article);
    List<ArticleLog> getLogs(Long articleId);
    List<ArticleLog> getLogs(Date date);
    List<ArticleLog> getLogs(User user);
}
