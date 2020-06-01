package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.EntityStatus;
import com.kach.studyhelperback.model.Like;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.LikeRepository;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.AuthService;
import com.kach.studyhelperback.service.LikeService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    ArticleService articleService;


    @Override
    public void addLike(Article article) throws Exception {
        if (authService.isAuthenticated()) {
            if (!checkLikedArticle(article.getId(), authService.getActiveUser().getId())){
                Like like = new Like();
                like.setArticle(article);
                like.setUser(authService.getActiveUser());
                likeRepository.save(like);
            } else {
                throw new Exception("user liked this article");
            }
        } else {
            throw new Exception("user isn't logged in");
        }
    }

    @Override
    public List<Like> getLikes() {
        return likeRepository.findAll();
    }

    @Override
    public List<Like> getLikes(Article article) {
        return likeRepository.findAllByArticle(article);
    }

    @Override
    public List<Like> getLikes(Long articleId) {
        return likeRepository.findAllByArticle_Id(articleId);
    }

    @Override
    public boolean checkLikedArticle(Long articleId, Long userId) {
        Optional<Like> like = likeRepository.findByUser_IdAndArticle_Id(userId, articleId);
        return !like.isEmpty() && like.get().getStatus() == EntityStatus.ACTIVE;
    }

    @Override
    public List<Like> getLikes(User user) {
        return likeRepository.findAllByUser_Id(user.getId());
    }

    @Override
    public void toggleArticleLike(Long articleId) {
        User user = authService.getActiveUser();
        Optional<Like> like = likeRepository.findByUser_IdAndArticle_Id(user.getId(), articleId);
        if (like.isEmpty()) {
            Like nLike = new Like();
            nLike.setArticle(articleService.getArticle(articleId));
            nLike.setUser(user);
            likeRepository.save(nLike);
        }
        else {
            if (like.get().getStatus()== EntityStatus.ACTIVE)
                like.get().setStatus(EntityStatus.DELETED);
            else
                like.get().setStatus(EntityStatus.ACTIVE);
            likeRepository.save(like.get());
        }
    }

    @Override
    public List<Article> getLikedArticles() {
        List<Like> likes = likeRepository.findAllByUser_IdAndStatus(authService.getActiveUser().getId(), EntityStatus.ACTIVE);
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < likes.size(); i++) {
            articles.add(likes.get(i).getArticle());
        }
        return articles;
    }
}
