package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.EntityStatus;
import com.kach.studyhelperback.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByArticle(Article article);
    List<Like> findAllByArticle_Id(Long articleId);
    List<Like> findAllByUser_Id(Long userId);
    Optional<Like> findByUser_IdAndArticle_Id(Long userId, Long articleId);
    List<Like> findAllByUser_IdAndStatus(Long userId, EntityStatus status);
}
