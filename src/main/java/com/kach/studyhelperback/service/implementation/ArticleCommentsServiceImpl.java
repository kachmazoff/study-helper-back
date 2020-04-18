package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.repository.ArticleCommentsRepository;
import com.kach.studyhelperback.service.ArticleCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentsServiceImpl implements ArticleCommentsService {

    @Autowired
    ArticleCommentsRepository articleCommentsRepository;

    @Override
    public void addArticleComment(ArticleComments articleComment) {
        if (articleComment.getArticle() == null || articleComment.getText() == null) {
            throw new IllegalArgumentException("Article or text comment is undefined");
        }

        articleComment.setId(null);
        if (articleComment.getText() == null)
            articleComment.setText("");
        articleCommentsRepository.save(articleComment);
    }

    @Override
    public void addReplyArticleComment(ArticleComments articleComment, ArticleComments replyArticleComment) {
        if (articleComment.getArticle() == null || articleComment.getText() == null) {
            throw new IllegalArgumentException("Article or text comment is undefined");
        }

        articleComment.setId(null);
        if (articleComment.getText() == null)
            articleComment.setText("");
        articleComment.setReplyComment(replyArticleComment);
    }

    @Override
    public List<ArticleComments> getArticleComment(Long articleId) {
        return articleCommentsRepository.findAllByArticleId(articleId);
    }

    @Override
    public List<ArticleComments> getArticleComment(Article article) {
        return articleCommentsRepository.findAllByArticle(article);
    }
}
