package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.ArticleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleType, Integer> {
    Optional<ArticleType> findByName(String name);
}
