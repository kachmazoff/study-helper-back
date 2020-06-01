package com.kach.studyhelperback.model.comparator;

import com.kach.studyhelperback.model.ArticlesRelations;

import java.util.Comparator;

public class ArticlesRelationsUsageComparator implements Comparator<ArticlesRelations> {
    @Override
    public int compare(ArticlesRelations articlesRelations, ArticlesRelations t1) {
        return articlesRelations.getUsageCounter().compareTo(t1.getUsageCounter());
    }
}
