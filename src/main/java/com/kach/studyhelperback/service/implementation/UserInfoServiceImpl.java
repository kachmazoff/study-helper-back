package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.model.UserInfo;
import com.kach.studyhelperback.repository.UserInfoRepository;
import com.kach.studyhelperback.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public void addUserInfoTool(User user, UserInfo userInfo) {
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserInfo(User user) {
        Optional<UserInfo> userInfo = userInfoRepository.findAllByUser_Id(user.getId());
        if (userInfo.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        return userInfo.get();
    }
}
