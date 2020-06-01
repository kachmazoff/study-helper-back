package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.dto.helper.ArticleLogMin;
import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleLog;
import com.kach.studyhelperback.model.ArticlesRelations;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.ArticlesRelationsService;
import com.kach.studyhelperback.service.LogService;
import com.kach.studyhelperback.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    LogService logService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticlesRelationsService articlesRelationsService;

    @Override
    public List<Article> get() {
        return getTopArticles();
    }

    @Override
    public List<Article> get(User user) {
        List<ArticleLog> logs = logService.getLogs();

        Map<Long, Integer> logsCounter = new HashMap<>();
        for (ArticleLog log : logs) {
            Long currArticleId = log.getArticle().getId();sy
            if (!logsCounter.containsKey(currArticleId))
                logsCounter.put(currArticleId, 1);
            else
                logsCounter.put(currArticleId, logsCounter.get(currArticleId) + 1);
        }

        logsCounter.forEach((l, v) -> {System.out.println(l.toString() + " " + v.toString());});

        return null;
    }

    @Override
    public List<Article> get(User user, Article article) {
        List<ArticlesRelations> relations = articlesRelationsService.getRelations(article, 1);
        List<Article> articles = new ArrayList<>();

        for (ArticlesRelations relation : relations) {
            if (relation.getTo().equals(article)) {
                articles.add(relation.getFrom());
            }
        }

        return articles;
    }

    @Override
    public List<Article> get(User user, Long articleId) {
        Article article = articleService.getArticle(articleId);
        return get(user, article);
    }

    @Override
    public List<Article> getTopArticles() {
        List<ArticleLogMin> logs_min = logService.getMinLogs();

        Map<Long, Integer> logsCounterMap = new HashMap<>();
        for (ArticleLogMin log : logs_min) {
            Long currArticleId = log.getArticle_Id();
            if (!logsCounterMap.containsKey(currArticleId))
                logsCounterMap.put(currArticleId, 1);
            else
                logsCounterMap.put(currArticleId, logsCounterMap.get(currArticleId) + 1);
        }

        List<Pair<Long, Integer>> logsCounter = new ArrayList<>();

        logsCounterMap.forEach((l, v) -> {logsCounter.add(Pair.of(l, v));});
        logsCounter.sort(Comparator.comparing(Pair::getSecond));
        Collections.reverse(logsCounter);

        List<Article> articles = new ArrayList<>();
        for(int i = 0, j = 0; i < logsCounter.size() && j < 10; i++, j++) {
            Pair curr = logsCounter.get(i);
            articles.add(articleService.getArticle((Long)curr.getFirst()));
        }

        return articles;
    }

    @Override
    public List<Article> getHotArticles() {
        return null;
    }
}
