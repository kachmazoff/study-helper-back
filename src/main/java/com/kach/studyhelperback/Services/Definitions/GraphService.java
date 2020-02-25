package com.kach.studyhelperback.Services.Definitions;

import com.kach.studyhelperback.Models.DAO.Article;
import java.util.List;

public interface GraphService {
    void topSort();
    List<Article> findPath(Integer startId, Integer endId);
}
