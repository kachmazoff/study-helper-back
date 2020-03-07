package com.kach.studyhelperback.Models.DAO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String header;

    @NotNull
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "article_type_id", nullable = false)
    private ArticleType type;

    @NotNull
    private Boolean isPublished;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }
}
