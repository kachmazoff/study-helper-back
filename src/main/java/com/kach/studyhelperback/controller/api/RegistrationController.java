package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.Role;
import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/account")
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity registration(@RequestBody Map<String, String> body) {
        userService.createUser(body.get("username"), body.get("password"));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        User user = userService.getUser("root");
        Set<Role> roles =  user.getRoles();
        for (Role role : roles) {
            System.out.println(role.getName());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
