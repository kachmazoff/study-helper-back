package com.kach.studyhelperback.Controllers.API;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import com.kach.studyhelperback.Models.DAO.Edge;
import com.kach.studyhelperback.Models.Helpers.ArticleMin;
import com.kach.studyhelperback.Models.Helpers.EdgeMin;
import com.kach.studyhelperback.Repositories.ArticleRepository;
import com.kach.studyhelperback.Repositories.ArticleTypeRepository;
//import com.kach.studyhelperback.Repositories.EdgeMinRepository;
import com.kach.studyhelperback.Repositories.EdgeRepository;
import com.kach.studyhelperback.Services.Definitions.ArticleService;
import com.kach.studyhelperback.Services.Definitions.ArticleTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @Autowired
    EdgeRepository edgeRepository;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypesService articleTypesService;

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam Optional<String> type) {
        if (type.isEmpty())
            return articleService.getAllArticles();
        else
            return articleService.getAllArticles(articleTypesService.getType(type.get()));
    }

    @PostMapping("")
    public ResponseEntity addArticle(@RequestBody Article article) {
        ArticleType type = articleTypeRepository.findById(article.getType().getId()).get();
        article.setType(type);

        articleService.addArticle(article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        return articleService.getArticle(id);
        // TODO: 404 handling
    }

    @PostMapping("/{id}")
    public ResponseEntity editArticle(@PathVariable("id") Integer id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteArticle(@PathVariable("id") Integer id) {
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

    @PostMapping("/types/{id}")
    public ResponseEntity editArticleType(@PathVariable("id") Integer id, @RequestBody ArticleType articleType) {
        articleTypesService.updateType(id, articleType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types/{id}/delete")
    public ResponseEntity deleteArticleType(@PathVariable("id") Integer id) {
        articleTypesService.deleteType(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}/relations")
    public List<Edge> getRelations(@PathVariable("id") Integer id) {
        return edgeRepository.findAllByFrom_Id(id);
    }

    @GetMapping("/{id}/relationsmin")
    public List<EdgeMin> getMinRelations(@PathVariable("id") Integer id) {
        return edgeRepository.getAllByFromId(id);
    }

    @PostMapping("/{id}/relations")
    public ResponseEntity addRelations(@PathVariable("id") Integer id, @RequestBody List<Integer> to) {
        Article fromArticle = articleRepository.findById(id).get();
        System.out.println(to.size());
        System.out.println(to.get(0));
        for (Integer toId : to) {
            Article toArticle = articleRepository.findById(toId).get();
            Edge edge = new Edge(fromArticle, toArticle);
            edge.setWeight(1.);
            edgeRepository.save(edge);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
