package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.config.ArticlesTransitionWeights;
import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.model.ArticleType;
import com.kach.studyhelperback.repository.ArticleRepository;
import com.kach.studyhelperback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypesService articleTypesService;

    @Autowired
    ArticleCommentsService articleCommentsService;

    @Autowired
    ArticleStatService articleStatService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam Optional<Long> type) {
        if (type.isEmpty())
            return articleService.getAllArticles();
        else
            return articleService.getAllArticles(articleTypesService.getType(type.get()));
    }

    @GetMapping("/new")
    public List<Article> getNewArticles() {
        return articleService.getNewArticles();
    }

    @GetMapping("/lastViewed")
    @PreAuthorize("isAuthenticated()")
    public List<Article> getLastViewedArticles() {
        return articleService.getLastViewedArticles();
    }


    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam String query) {
        return articleRepository.findAllByTitleContains(query);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public List<Article> getAllMyArticles() {
        return articleService.getAllMyArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getFullArticle(id);
        // TODO: 404 handling
    }

    @GetMapping("/types")
    public List<ArticleType> getAllArticlesTypes() {
        return articleTypesService.getAllTypes();
    }

    @GetMapping("/types/{id}")
    public ArticleType getArticleType(@PathVariable("id") Long id) {
        return articleTypesService.getType(id);
    }

    @GetMapping("/{id}/comments")
    public List<ArticleComments> getArticleComments (@PathVariable("id") Long articleId){
        return articleCommentsService.getArticleComments(articleId);
    }

    @PostMapping("/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity addComment(@PathVariable("id") Long articleId, @RequestBody ArticleComments articleComment){
        articleComment.setArticle(articleService.getArticle(articleId));
        articleCommentsService.addArticleComment(articleComment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/stat")
    public Long getArticleViews(@PathVariable("id") Long articleId) {
        return articleStatService.getArticleViews(articleId);
    }

    @GetMapping("/{id}/recommendations")
    @PreAuthorize("isAuthenticated()")
    public List<Article> getRecommendations(@PathVariable("id") Long id) {
        return recommendationService.get(authService.getActiveUser(), id);
    }

    @GetMapping("/recommendations")
    public List<Article> getCommonRecommendations() {
        return recommendationService.get();
    }

    @GetMapping("/{from}/recommendation/{to}/use")
    public Article useRecommendation(@PathVariable("from") Long from, @PathVariable("to") Long to) {
        return recommendationService.useRecommendation(from, to, ArticlesTransitionWeights.DIRECT);
    }
}
