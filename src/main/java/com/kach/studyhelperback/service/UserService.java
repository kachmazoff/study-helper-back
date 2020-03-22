package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;

public interface UserService {
    void createUser(String username, String password);
    User getUser(String username);
    void changePassword(String username, String oldPassword, String newPassword);
//    public abstract void resetPassword()
    void createNewUserRole(String roleName);
    void deleteUserRole(String roleName);
    Iterable<Role> getUserRoles(String username);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
}
