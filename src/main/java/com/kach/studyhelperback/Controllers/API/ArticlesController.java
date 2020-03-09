package com.kach.studyhelperback.Controllers.API;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import com.kach.studyhelperback.Repositories.ArticleRepository;
import com.kach.studyhelperback.Repositories.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @GetMapping("")
    public List<Article> getAllArticles(@RequestParam Optional<String> type) {
        List<Article> result = new ArrayList<>();

//        Optional<ArticleType> curType = articleTypeRepository.findByName(type.get());
//        if (curType.isPresent()) {
//            articleRepository.findAllByType(curType.get()).forEach(result::add);
//        } else {
        articleRepository.findAll().forEach(result::add);
//        }
        return result;
    }

    @PostMapping("")
    public ResponseEntity addArticle(@RequestBody Article article) {
        article.setId(null);
        ArticleType type = articleTypeRepository.findById(article.getType().getId()).get();
        article.setType(type);
        articleRepository.save(article);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        return  article.get();
        // TODO: 404 handling
//        if (!article.isPresent())
//            return new ResponseEntity<ArticleType>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity editArticle(@PathVariable("id") Integer id, @RequestBody Article article) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }

        Article target = optionalArticle.get();

        if (article.getIsPublished() != null)
            target.setPublished(article.getIsPublished());
        if (article.getHeader() != null)
            target.setHeader(article.getHeader());
        if (article.getText() != null)
            target.setText(article.getText());

        if (article.getType() != null) {
            if (article.getType().getId() != null) {
                Integer typeId = article.getType().getId();
                if (!articleTypeRepository.existsById(typeId))
                    return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
                // TODO: optimization
                optionalArticle.get().setType(articleTypeRepository.findById(typeId).get());
            }
        }

        articleRepository.save(target);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteArticle(@PathVariable("id") Integer id) {
        if (!articleRepository.existsById(id))
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        articleRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/types")
    public List<ArticleType> getAllArticlesTypes() {
        List<ArticleType> result = new ArrayList<>();
        articleTypeRepository.findAll().forEach(result::add);
        return result;
    }

    @PostMapping("/types")
    public ResponseEntity createArticleType(@RequestBody ArticleType articleType) {
        articleType.setId(null);
        articleTypeRepository.save(articleType);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types/{id}")
    public ResponseEntity editArticleType(@PathVariable("id") Integer id, @RequestBody ArticleType articleType) {
        if (!articleTypeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ArticleType target = articleTypeRepository.findById(id).get();
        if (articleType.getName() != null)
            target.setName(articleType.getName());
        articleTypeRepository.save(target);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/types/{id}/delete")
    public ResponseEntity deleteArticleType(@PathVariable("id") Integer id) {
        if (!articleTypeRepository.existsById(id))
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        articleTypeRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
