package com.kach.studyhelperback.repository;

import com.kach.studyhelperback.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findAllByUser_Id(Long id);
}
