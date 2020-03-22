package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.ArticleType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleType, Long> {
    Optional<ArticleType> findByName(String name);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
