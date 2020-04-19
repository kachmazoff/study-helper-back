package com.kach.studyhelperback.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="articles_relations")
public class ArticlesRelations extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "from_id", nullable = false, referencedColumnName = "id")
    private Article from;

    @OneToOne
    @JoinColumn(name = "to_id", nullable = false)
    private Article to;

    @NotNull
    private Double weight = 0.;

    private Integer usageCounter = 0;
}
