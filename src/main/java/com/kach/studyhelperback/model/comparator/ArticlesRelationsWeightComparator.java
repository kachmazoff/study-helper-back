package com.kach.studyhelperback.model.comparator;

import com.kach.studyhelperback.model.ArticlesRelations;

import java.util.Comparator;

public class ArticlesRelationsWeightComparator implements Comparator<ArticlesRelations> {
    @Override
    public int compare(ArticlesRelations articlesRelations, ArticlesRelations t1) {
        return articlesRelations.getWeight().compareTo(t1.getWeight());
    }
}
