package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.*;
import com.kach.studyhelperback.repository.LikeRepository;
import com.kach.studyhelperback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    AuthService authService;

    @GetMapping("/all")
    public List<Like> getAllLikes(){
        return likeRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{id}/favorites")
    public List<Like> getLikesOfUser(@PathVariable("id") Long userId){
        return likeRepository.findAllByUser_Id(userId);
    }

    @GetMapping("/article/{id}")
    public List<Like> getLikeOfArticle(@PathVariable("id") Long articleId){
        return likeRepository.findAllByArticle_Id(articleId);
    }

    @GetMapping("/checkLike/{id}")
    public boolean checkLike(@PathVariable("id") Long articleId) throws Exception {
        if (authService.isAuthenticated()){
            return likeService.checkLikedArticle(articleId, authService.getActiveUser().getId());
        } else {
            throw new Exception("user isn't logged in");
        }
    }
}
