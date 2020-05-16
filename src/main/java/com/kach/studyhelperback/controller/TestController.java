package com.kach.studyhelperback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/secure")
    @PreAuthorize("isAuthenticated()") // Expression-Based Access Control
    String secureRoute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
//        return "secret";
    }

    @PostMapping("/map")
    ResponseEntity mapBody(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/secure/role")
    @PreAuthorize("isAuthenticated()&&hasRole('admin')") // Not working. Need role checking in java
    ResponseEntity testRoleSecure() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
