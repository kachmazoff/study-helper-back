package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    UserService userService;

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public User getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(authentication.getPrincipal().toString());
    }
}
