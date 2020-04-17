package com.kach.studyhelperback.model;

import javax.persistence.*;

@Entity
@Table(name = "article_log")
public class ArticleLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "articles_id", nullable = false)
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
