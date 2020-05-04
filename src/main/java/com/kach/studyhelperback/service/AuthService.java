package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.User;

public interface AuthService {
    boolean isAuthenticated();
    User getActiveUser();
}
