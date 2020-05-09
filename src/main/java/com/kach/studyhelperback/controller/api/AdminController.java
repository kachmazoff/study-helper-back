package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @PostMapping("/articles/relations")
    public ResponseEntity updateArticlesRelations() {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return userService.getAllRoles();
    }

    @PostMapping("/roles")
    public ResponseEntity createNewRole(@RequestBody Map<String, String> body) {
        userService.createNewUserRole(body.get("roleName"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/users/{userId}/roles")
    public ResponseEntity addRolesToUser(@PathVariable("userId") Long userId, @RequestBody List<Long> roles) {
        roles.forEach(roleId -> userService.addRoleToUser(userId, roleId));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
