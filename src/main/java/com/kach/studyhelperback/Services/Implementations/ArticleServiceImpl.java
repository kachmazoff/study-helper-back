package com.kach.studyhelperback.Services.Implementations;

import com.kach.studyhelperback.Models.DAO.Article;
import com.kach.studyhelperback.Models.DAO.ArticleType;
import com.kach.studyhelperback.Repositories.ArticleRepository;
import com.kach.studyhelperback.Repositories.ArticleTypeRepository;
import com.kach.studyhelperback.Services.Definitions.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @Override
    public Optional<Article> getArticle(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Iterable<Article> getAllArticles(ArticleType type) {
        return articleRepository.findAll();
        // TODO: change it!!
    }

    @Override
    public Integer createArticle(String header, String articleType, String text) {
        Article newArticle = new Article();
        newArticle.setHeader(header);
        newArticle.setText(text);

        // TODO: find articleType and set to newArticle
        return articleRepository.save(newArticle).getId();
    }

    @Override
    public void setArticlePublished(Boolean isPublished) {

    }

    @Override
    public void updateArticleText(Integer articleId, String text) {

    }

    @Override
    public void updateArticleHeader(Integer articleId, String header) {

    }

    @Override
    public void updateArticleType(Integer articleId, String typeName) {

    }

    @Override
    public Optional<ArticleType> getArticleType(Integer id) {
        return articleTypeRepository.findById(id);
    }

    @Override
    public Optional<ArticleType> getArticleType(String name) {
        return null;
    }

    @Override
    public Iterable<ArticleType> getArticleTypes() {
        return articleTypeRepository.findAll();
    }

    @Override
    public Integer createArticleType(String name) {
        return null;
    }

    @Override
    public void deleteArticleType(String name) {

    }

    @Override
    public void renameArticleType(String oldName, String newName) {

    }
}
