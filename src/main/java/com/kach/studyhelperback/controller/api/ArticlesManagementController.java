package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;
import com.kach.studyhelperback.model.ArticlesRelations;
import com.kach.studyhelperback.repository.ArticleTypeRepository;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.ArticleTypesService;
import com.kach.studyhelperback.service.ArticlesRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesManagementController {
    @Autowired
    ArticleTypeRepository articleTypeRepository;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleTypesService articleTypesService;
    @Autowired
    ArticlesRelationsService articlesRelationsService;

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity addArticle(@RequestBody Article article) {
        ArticleType type = articleTypeRepository.findById(article.getType().getId()).get();
        article.setType(type);

        articleService.addArticle(article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity editArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types")
    public ResponseEntity createArticleType(@RequestBody ArticleType articleType) {
        articleTypesService.addType(articleType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types/{id}")
    public ResponseEntity editArticleType(@PathVariable("id") Long id, @RequestBody ArticleType articleType) {
        articleTypesService.updateType(id, articleType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types/{id}/delete")
    public ResponseEntity deleteArticleType(@PathVariable("id") Long id) {
        articleTypesService.deleteType(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/relations")
    public List<ArticlesRelations> getRelations(@PathVariable("id") Long id) {
        return articlesRelationsService.getRelations(id, 1);
    }

    @PostMapping("/{id}/relations")
    public ResponseEntity addRelations(@PathVariable("id") Long id, @RequestBody List<Long> dependencies) {
        System.out.println(dependencies.size());
        System.out.println(dependencies.get(0));
        for (Long dependenceId : dependencies) {
            articlesRelationsService.addRelation(dependenceId, id, 1.);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/dependencies")
    public List<Article> getDependencies(@PathVariable("id") Long id) {
        List<Article> dependencies = new ArrayList<>();
        List<ArticlesRelations> relations = articlesRelationsService.getRelations(id, 1);
        for (ArticlesRelations relation : relations) {
            dependencies.add(relation.getFrom());
        }
        return dependencies;
    }

    @PostMapping("/{id}/dependencies")
    public ResponseEntity addDependencies(@PathVariable("id") Long id, @RequestBody List<Long> dependencies) {
        System.out.println(dependencies.size());
        System.out.println(dependencies.get(0));
        for (Long dependenceId : dependencies) {
            articlesRelationsService.addRelation(dependenceId, id, 1.);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
