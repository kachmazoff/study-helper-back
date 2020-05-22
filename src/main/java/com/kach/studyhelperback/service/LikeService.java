package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.Like;
import com.kach.studyhelperback.model.User;

import java.util.Date;
import java.util.List;

public interface LikeService {
    void Likes(Article article) throws Exception;
    List<Like> getLikes();
    List<Like> getLikes(Article article);
    List<Like> getLikes(Long articleId);
    boolean checkLikedArticle(Long articleId, Long userId);
    List<Like> getLikes(User user);
}
