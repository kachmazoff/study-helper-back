package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.ArticlesRelations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticlesRelationsRepository extends CrudRepository<ArticlesRelations, Long> {
    List<ArticlesRelations> findAllByFrom_Id(Long fromId);
    List<ArticlesRelations> findAllByTo_Id(Long toId);
}
