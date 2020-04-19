package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.ArticleCommentsRepository;
import com.kach.studyhelperback.service.ArticleCommentsService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleCommentsServiceImpl implements ArticleCommentsService {

    @Autowired
    ArticleCommentsRepository articleCommentsRepository;

    @Autowired
    UserService userService;

    @Override
    public void addArticleComment(ArticleComments articleComment) {
        if (articleComment.getArticle() == null || articleComment.getText() == null) {
            throw new IllegalArgumentException("Article or text comment is undefined");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User creator = userService.getUser(authentication.getPrincipal().toString());

        articleComment.setCreator(creator);

        articleComment.setId(null);
        if (articleComment.getText() == null)
            articleComment.setText("");
        articleCommentsRepository.save(articleComment);
    }

//    @Override
//    public void addReplyArticleComment(ArticleComments articleComment, ArticleComments replyArticleComment) {
//        if (articleComment.getArticle() == null || articleComment.getText() == null) {
//            throw new IllegalArgumentException("Article or text comment is undefined");
//        }
//
//        articleComment.setId(null);
//        if (articleComment.getText() == null)
//            articleComment.setText("");
//        articleComment.setReplyComment(replyArticleComment);
//    }

    @Override
    public void addReplyArticleComment(Long articleCommentId, ArticleComments replyArticleComment) {
        if(articleCommentsRepository.findById(articleCommentId).isEmpty()){
            throw new IllegalArgumentException("Not found");
        }
        ArticleComments articleComment = articleCommentsRepository.findById(articleCommentId).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User creator = userService.getUser(authentication.getPrincipal().toString());

        replyArticleComment.setCreator(creator);

        articleComment.setId(null);
        if (articleComment.getText() == null)
            articleComment.setText("");
        articleComment.setReplyComment(replyArticleComment);
    }

    @Override
    public List<ArticleComments> getArticleComments(Long articleId) {
        return articleCommentsRepository.findAllByArticle_Id(articleId);
    }

    @Override
    public List<ArticleComments> getArticleComments(Article article) {
        return articleCommentsRepository.findAllByArticle(article);
    }

    @Override
    public Optional<ArticleComments> getArticleComment(Long articleCommentId) {
        return articleCommentsRepository.findById(articleCommentId);
    }
}
