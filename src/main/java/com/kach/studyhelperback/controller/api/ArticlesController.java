package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.model.ArticleType;
import com.kach.studyhelperback.repository.ArticleRepository;
import com.kach.studyhelperback.repository.ArticleTypeRepository;
import com.kach.studyhelperback.service.ArticleCommentsService;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.ArticleStatService;
import com.kach.studyhelperback.service.ArticleTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//import com.kach.studyhelperback.models.Edge;
//import com.kach.studyhelperback.dto.Helpers.EdgeMin;
//import com.kach.studyhelperback.Repositories.EdgeMinRepository;
//import com.kach.studyhelperback.repositories.EdgeRepository;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

//    @Autowired
//    EdgeRepository edgeRepository;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypesService articleTypesService;

    @Autowired
    ArticleCommentsService articleCommentsService;

    @Autowired
    ArticleStatService articleStatService;

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam Optional<Long> type) {

        if (type.isEmpty())
            return articleService.getAllArticles();
        else
            return articleService.getAllArticles(articleTypesService.getType(type.get()));
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public List<Article> getAllMyArticles() {
        return articleService.getAllMyArticles();
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity addArticle(@RequestBody Article article) {
        ArticleType type = articleTypeRepository.findById(article.getType().getId()).get();
        article.setType(type);

        articleService.addArticle(article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
        // TODO: 404 handling
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

    @GetMapping("/types")
    public List<ArticleType> getAllArticlesTypes() {
        return articleTypesService.getAllTypes();
    }

    @PostMapping("/types")
    public ResponseEntity createArticleType(@RequestBody ArticleType articleType) {
        articleTypesService.addType(articleType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/types/{id}")
    public ArticleType getArticleType(@PathVariable("id") Long id) {
        return articleTypesService.getType(id);
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
        return articleStatService.getArticleStat(articleId);
    }

//    @GetMapping("/{id}/relations")
//    public List<Edge> getRelations(@PathVariable("id") Integer id) {
//        return edgeRepository.findAllByFrom_Id(id);
//    }
//
//    @GetMapping("/{id}/relationsmin")
//    public List<EdgeMin> getMinRelations(@PathVariable("id") Integer id) {
//        return edgeRepository.getAllByFromId(id);
//    }
//
//    @PostMapping("/{id}/relations")
//    public ResponseEntity addRelations(@PathVariable("id") Integer id, @RequestBody List<Integer> to) {
//        Article fromArticle = articleRepository.findById(id).get();
//        System.out.println(to.size());
//        System.out.println(to.get(0));
//        for (Integer toId : to) {
//            Article toArticle = articleRepository.findById(toId).get();
//            Edge edge = new Edge(fromArticle, toArticle);
//            edge.setWeight(1.);
//            edgeRepository.save(edge);
//        }
//
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
}
