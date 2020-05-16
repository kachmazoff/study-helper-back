package com.kach.studyhelperback.service;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;

import java.util.List;

public interface UserService {
    void createUser(String username, String password);
    User getUser(String username);
    User getUser(Long id);
    void changePassword(String username, String oldPassword, String newPassword);
//    public abstract void resetPassword()
    void createNewUserRole(String roleName);
    void deleteUserRole(String roleName);
    List<Role> getUserRoles(String username);
    List<Role> getAllRoles();
    Role getRole(Long id);
    void addRoleToUser(User user, Role role);
    void addRoleToUser(String username, String roleName);
    void addRoleToUser(Long userId, Long roleId);
    void removeRoleFromUser(String username, String roleName);
}
