package com.kach.studyhelperback.Services.Implementations;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import com.kach.studyhelperback.Repositories.ArticleRepository;
import com.kach.studyhelperback.Services.Definitions.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article getArticle(Integer id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }

        return article.get();
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return articles;
    }

    @Override
    public List<Article> getAllArticles(ArticleType type) {
        return articleRepository.findAllByType(type);
    }

    @Override
    public void addArticle(Article article) {
        if (article.getHeader() == null || article.getType() == null) {
            throw new IllegalArgumentException("Header or articleType is undefined");
        }

        article.setId(null);
        article.setPublished(false);
        if (article.getText() == null)
            article.setText("");
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Integer articleId, Article updatedArticle) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();

//        if (article.getType() != null) {
//            if (article.getType().getId() != null) {
//                Integer typeId = article.getType().getId();
//                if (!articleTypeRepository.existsById(typeId))
//                    return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
//                // TODO: optimization
//                optionalArticle.get().setType(articleTypeRepository.findById(typeId).get());
//            }
//        }

        if (updatedArticle.getHeader() != null)  target.setHeader(updatedArticle.getHeader());
        if (updatedArticle.getText() != null)  target.setText(updatedArticle.getText());
        if (updatedArticle.getType() != null)  target.setType(updatedArticle.getType());
        if (updatedArticle.getIsPublished() != null)  target.setPublished(updatedArticle.getIsPublished());

        articleRepository.save(target);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        articleRepository.deleteById(articleId);
    }

    @Override
    public void setArticlePublished(Integer articleId, Boolean isPublished) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();
        target.setPublished(isPublished);
        articleRepository.save(target);
    }
}
