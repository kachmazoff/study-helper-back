package com.kach.studyhelperback.Services.Implementations;

import com.kach.studyhelperback.Models.DAO.Role;
import com.kach.studyhelperback.Models.DAO.User;
import com.kach.studyhelperback.Repositories.RoleRepository;
import com.kach.studyhelperback.Repositories.UserRepository;
import com.kach.studyhelperback.Services.Definitions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void createUser(String login, String password, String displayName) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password); // TODO: ENCODING!!!
        newUser.setDisplayName(displayName);
        userRepository.save(newUser);
    }

    @Override
    public User getUser(String login) {
        return null;
    }

    @Override
    public void changePassword(String login, String oldPassword, String newPassword) {

    }

    @Override
    public void createNewUserRole(String roleName) {

    }

    @Override
    public void deleteUserRole(String roleName) {

    }

    @Override
    public Iterable<Role> getUserRoles(String login) {
        // TODO: find user by login
        //then user.roles
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(String login, String roleName) {

    }

    @Override
    public void removeRoleFromUser(String login, String roleName) {

    }
}
