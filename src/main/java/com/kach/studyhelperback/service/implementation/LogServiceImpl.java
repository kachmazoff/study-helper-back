package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.dto.helper.ArticleLogMin;
import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleLog;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.ArticleLogRepository;
import com.kach.studyhelperback.service.AuthService;
import com.kach.studyhelperback.service.LogService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    ArticleLogRepository articleLogRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Override
    public List<ArticleLog> getLogs(User user) {
        return articleLogRepository.findAllByUser_Id(user.getId());
    }

    @Override
    public void log(Article article) {
        ArticleLog log = new ArticleLog();
        log.setArticle(article);
        if (authService.isAuthenticated()) {
            log.setUser(authService.getActiveUser());
        }
        articleLogRepository.save(log);
    }

    @Override
    public List<ArticleLog> getLogs() {
        return articleLogRepository.findAll();
    }

    @Override
    public List<ArticleLog> getLogs(Article article) {
        return articleLogRepository.findAllByArticle(article);
    }

    @Override
    public List<ArticleLog> getLogs(Long articleId) {
        return articleLogRepository.findAllByArticle_Id(articleId);
    }

    @Override
    public List<ArticleLog> getLogs(Date date) {
        return articleLogRepository.findAllByCreated(date);
    }

    @Override
    public List<ArticleLogMin> getMinLogs() {
        return articleLogRepository.findAllBy();
    }
}
