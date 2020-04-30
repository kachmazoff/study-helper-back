package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleType;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.ArticleRepository;
import com.kach.studyhelperback.repository.ArticleTypeRepository;
import com.kach.studyhelperback.service.ArticleService;
import com.kach.studyhelperback.service.LogService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleTypeRepository articleTypeRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    LogService logService;
    @Autowired
    UserService userService;

    @Override
    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        logService.log(article.get());
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
    public List<Article> getAllMyArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currUser = userService.getUser(authentication.getPrincipal().toString());
        return articleRepository.findAllByCreator_Id(currUser.getId());
    }

    @Override
    public void addArticle(Article article) {
        if (article.getTitle() == null || article.getType() == null) {
            throw new IllegalArgumentException("Header or articleType is undefined");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User creator = userService.getUser(authentication.getPrincipal().toString());

        article.setCreator(creator);
        article.setId(null);
        article.setPublished(false);
        if (article.getContent() == null)
            article.setContent("");
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(Long articleId, Article updatedArticle) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();

        if (updatedArticle.getType() != null) {
            if (updatedArticle.getType().getId() != null) {
                Long typeId = updatedArticle.getType().getId();
//                if (!articleTypeRepository.existsById(typeId))
//                    return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
                // TODO: optimization
                target.setType(articleTypeRepository.findById(typeId).get());
            }
        }

        if (updatedArticle.getTitle() != null)  target.setTitle(updatedArticle.getTitle());
        if (updatedArticle.getContent() != null)  target.setContent(updatedArticle.getContent());
        if (updatedArticle.getType() != null)  target.setType(updatedArticle.getType());
        if (updatedArticle.getPublished() != null)  target.setPublished(updatedArticle.getPublished());

        articleRepository.save(target);
    }

    @Override
    public void deleteArticle(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        articleRepository.deleteById(articleId);
    }

    @Override
    public void setArticlePublished(Long articleId, Boolean isPublished) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("This article does not exist");
        }
        Article target = articleRepository.findById(articleId).get();
        target.setPublished(isPublished);
        articleRepository.save(target);
    }
}
