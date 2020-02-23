package com.kach.studyhelperback.Repositories;

import com.kach.studyhelperback.Models.DAO.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
