package com.kach.studyhelperback.service.implementation;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.repository.RoleRepository;
import com.kach.studyhelperback.repository.UserRepository;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        return user.get();
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {

    }

    @Override
    public void createNewUserRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

    @Override
    public void deleteUserRole(String roleName) {

    }

    @Override
    public List<Role> getUserRoles(String username) {
        // TODO: find user by login
        //then user.roles
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roles.addAll(roleRepository.findAll());
        return roles;
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        return role.get();
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        user.addNewRole(role);
        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).get();
        Role role = roleRepository.findByName(roleName).get();
        addRoleToUser(user, role);
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        addRoleToUser(getUser(userId), getRole(roleId));
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

    }
}
