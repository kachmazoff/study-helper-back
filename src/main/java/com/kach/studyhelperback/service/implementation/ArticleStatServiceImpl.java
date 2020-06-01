package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.ArticleLog;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.ArticleLogRepository;
import com.kach.studyhelperback.service.ArticleStatService;
import com.kach.studyhelperback.service.AuthService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleStatServiceImpl implements ArticleStatService {

    @Autowired
    ArticleLogRepository articleLogRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Override
    public Long getArticleViews(Long articleId) {
        List<ArticleLog> articleLogList = articleLogRepository.findAllByArticle_Id(articleId);
        return (Long) (long) articleLogList.size();
    }

//    @Override
//    public Long getArticlesStat() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User creator = userService.getUser(authentication.getPrincipal().toString());
//
//        List<ArticleLog> articleLogList = articleLogRepository.findAllByArticle_Creator_Id(creator.getId());
//        return (Long) (long) articleLogList.size();
//    }

    @Override
    public Long getArticlesStatByDate(Date date) {
        User creator = authService.getActiveUser();

        List<ArticleLog> articleLogList = articleLogRepository.findAllByArticle_Creator_IdAndArticle_Created(
                creator.getId(), date);
        return (Long) (long) articleLogList.size();
    }

    @Override
    public HashMap<String, Long> getArticlesStat() {
        User creator = authService.getActiveUser();

        List<ArticleLog> articleLogList = articleLogRepository.findAllByArticle_Creator_Id(creator.getId());

        HashMap<String, Long> articleLogHashMap = new HashMap<>();

        String dateStr;
        Long counter = (Long) (long) 1;
        for (ArticleLog articleLog : articleLogList) {
            Date date = articleLog.getCreated();
            dateStr = date.getDay() + "." + date.getMonth() + "." + date.getYear();
            if (!articleLogHashMap.containsKey(dateStr)) {
                articleLogHashMap.put(dateStr, counter);
            } else {
                articleLogHashMap.put(dateStr, articleLogHashMap.get(dateStr) + counter);
            }
        }
        return articleLogHashMap;
    }
}
