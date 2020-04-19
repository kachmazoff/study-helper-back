package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.RoleRepository;
import com.kach.studyhelperback.repository.UserRepository;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(String login, String password) {
        User newUser = new User();
        newUser.setUsername(login);
        newUser.setPassword(passwordEncoder.encode(password)); // TODO: ENCODING!!!
        userRepository.save(newUser);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {

    }

    @Override
    public void createNewUserRole(String roleName) {

    }

    @Override
    public void deleteUserRole(String roleName) {

    }

    @Override
    public Iterable<Role> getUserRoles(String username) {
        // TODO: find user by login
        //then user.roles
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

    }
}
