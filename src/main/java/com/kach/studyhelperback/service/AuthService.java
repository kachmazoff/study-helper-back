package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.User;

public interface AuthService {
    /**
     * Проверка пользователя на аутентификацию
     * @return true - если залогинен, false - если пользователь анонимный
     */
    boolean isAuthenticated();

    /**
     * Получить текущего пользователя
     * @return Объект User текущего пользователя
     */
    User getActiveUser();
}
