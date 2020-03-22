package com.kach.studyhelperback.dto.helper;

import com.kach.studyhelperback.model.Article;

public class ArticleId {
    private Long id;

    public ArticleId() {
    }

    public ArticleId(Long id) {
        this.id = id;
    }

    public ArticleId(Article article) {
        this.id = article.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
