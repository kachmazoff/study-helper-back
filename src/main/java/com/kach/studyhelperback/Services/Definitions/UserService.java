package com.kach.studyhelperback.Services.Definitions;

import com.kach.studyhelperback.Models.DAO.Role;
import com.kach.studyhelperback.Models.DAO.User;

public interface UserService {
    void createUser(String login, String password, String displayName);
    User getUser(String login);
    void changePassword(String login, String oldPassword, String newPassword);
//    public abstract void resetPassword()
    void createNewUserRole(String roleName);
    void deleteUserRole(String roleName);
    Iterable<Role> getUserRoles(String login);
    void addRoleToUser(String login, String roleName);
    void removeRoleFromUser(String login, String roleName);
}
