package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.config.ArticlesTransitionWeights;
import com.kach.studyhelperback.model.*;
import com.kach.studyhelperback.repository.ArticleRepository;
import com.kach.studyhelperback.repository.ArticleTypeRepository;
import com.kach.studyhelperback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleTypeRepository articleTypeRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    LogService logService;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    ArticlesRelationsService articlesRelationsService;

    @Override
    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        return article.get();
    }

    @Override
    public Article getFullArticle(Long id) {
        Article article = getArticle(id);

        if (authService.isAuthenticated()) {
            User user = authService.getActiveUser();
            List<ArticleLog> logs = logService.getLogs(user);

            Date date = new Date();
            final Long maxTimeDelta = 1000l * 60 * 60 * 24;
            // Обновляем связи для статей, посещённых в последние 24 часа
            for (int i = 0; i < logs.size(); i++) {
                ArticleLog log = logs.get(i);
                if (date.getTime() - log.getCreated().getTime() < maxTimeDelta && id != log.getArticle().getId())
                    articlesRelationsService.useOrAddRelation(log.getArticle(), article, ArticlesTransitionWeights.RANDOM);
            }
        }

        logService.log(article);
        return article;
    }

    @Override
    public List<Article> getNewArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        Collections.reverse(articles);

        List<Article> newArticles = new ArrayList<>();
        for (int i = 0; i < 10 && i < articles.size(); i++)
            newArticles.add(articles.get(i));

        return newArticles;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return articles;
    }

    @Override
    public List<Article> getAllArticles(ArticleType type) {
        return articleRepository.findAllByType(type);
    }

    @Override
    public List<Article> getLastViewedArticles() {
        List<ArticleLog> logs = logService.getLogs(authService.getActiveUser());
        Collections.reverse(logs);

        List<Long> articlesId = new ArrayList<>();
        List<Article> articles = new ArrayList<>();

        for (int i = 0; i < logs.size() && articlesId.size() < 5; i++) {
            if (!articlesId.contains(logs.get(i).getArticle().getId())) {
                articlesId.add(logs.get(i).getArticle().getId());
                articles.add(logs.get(i).getArticle());
            }
        }

        return articles;
    }

    @Override
    public List<Article> getAllMyArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currUser = userService.getUser(authentication.getPrincipal().toString());
        return articleRepository.findAllByCreator_Id(currUser.getId());
    }

    @Override
    public void addArticle(Article article) {
        if (article.getTitle() == null || article.getType() == null) {
            throw new IllegalArgumentException("Header or articleType is undefined");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User creator = userService.getUser(authentication.getPrincipal().toString());

        article.setCreator(creator);
        article.setId(null);
        article.setPublished(false);
        if (article.getContent() == null)
            article.setContent("");
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Long articleId, Article updatedArticle) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();

        if (updatedArticle.getType() != null) {
            if (updatedArticle.getType().getId() != null) {
                Long typeId = updatedArticle.getType().getId();
//                if (!articleTypeRepository.existsById(typeId))
//                    return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
                // TODO: optimization
                target.setType(articleTypeRepository.findById(typeId).get());
            }
        }

        if (updatedArticle.getTitle() != null)  target.setTitle(updatedArticle.getTitle());
        if (updatedArticle.getContent() != null)  target.setContent(updatedArticle.getContent());
        if (updatedArticle.getType() != null)  target.setType(updatedArticle.getType());
        if (updatedArticle.getPublished() != null)  target.setPublished(updatedArticle.getPublished());

        articleRepository.save(target);
    }

    @Override
    public void deleteArticle(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        articleRepository.deleteById(articleId);
    }

    @Override
    public void setArticlePublished(Long articleId, Boolean isPublished) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();
        target.setPublished(isPublished);
        articleRepository.save(target);
    }
}
