package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.model.UserInfo;

public interface UserInfoService {
    void addUserInfoTool (User user, UserInfo userInfo);
    UserInfo getUserInfo (User user);
}
