package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.service.AuthService;
import com.kach.studyhelperback.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    AuthService authService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/favorites")
    public List<Article> getLikedArticlesOfUser(){
        return likeService.getLikedArticles();
    }

    @GetMapping("/check/{id}")
    public boolean checkLike(@PathVariable("id") Long articleId) throws Exception {
        if (authService.isAuthenticated()){
            return likeService.checkLikedArticle(articleId, authService.getActiveUser().getId());
        } else {
            throw new Exception("user isn't logged in");
        }
    }

    @PostMapping("/article/{id}")
    public ResponseEntity changeArticleLike(@PathVariable("id") Long articleId) {
        likeService.toggleArticleLike(articleId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
