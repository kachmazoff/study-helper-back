package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.Edge;
import com.kach.studyhelperback.Models.Helpers.EdgeMin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdgeRepository extends JpaRepository<Edge, Integer> {
    List<Edge> findAllByFrom_Id(Integer fromId);
    List<EdgeMin> getAllByFromId(Integer fromId);
//    List<EdgeMin> ();
//    Iterable<Edge>
}
