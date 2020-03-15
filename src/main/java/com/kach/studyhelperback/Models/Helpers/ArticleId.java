package com.kach.studyhelperback.Models.Helpers;

import com.kach.studyhelperback.Models.DAO.Article;

public class ArticleId {
    private Integer id;

    public ArticleId() {
    }

    public ArticleId(Integer id) {
        this.id = id;
    }

    public ArticleId(Article article) {
        this.id = article.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
