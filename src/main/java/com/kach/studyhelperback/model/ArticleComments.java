package com.kach.studyhelperback.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "article_comments")
public class ArticleComments extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "article_id", nullable = false)
    Article article;

    @NotNull
    String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "reply_comment_id")
    ArticleComments replyComment;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArticleComments getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(ArticleComments replyComment) {
        this.replyComment = replyComment;
    }
}
