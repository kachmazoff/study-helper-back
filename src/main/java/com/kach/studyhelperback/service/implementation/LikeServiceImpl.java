package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.Like;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.LikeRepository;
import com.kach.studyhelperback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void Likes(Article article) throws Exception {
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
        if (likeRepository.findByUser_idAndArticle_id(articleId, userId) == null){
            return false;
        }
        return true;
    }

    @Override
    public List<Like> getLikes(User user) {
        return likeRepository.findAllByUser_Id(user.getId());
    }


}
