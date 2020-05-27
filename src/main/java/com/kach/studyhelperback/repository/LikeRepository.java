package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.Article;
import com.kach.studyhelperback.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByArticle(Article article);
    List<Like> findAllByArticle_Id(Long articleId);
    List<Like> findAllByUser_Id(Long userId);
    List<Like> findByUser_IdAndArticle_Id(Long userId, Long articleId);


}
