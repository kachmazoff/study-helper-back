package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.User;
import com.kach.studyhelperback.model.UserInfo;
import com.kach.studyhelperback.service.AuthService;
import com.kach.studyhelperback.service.UserInfoService;
import com.kach.studyhelperback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    AuthService authService;

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public User getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(authentication.getPrincipal().toString());
    }

    @PostMapping("/info")
    public ResponseEntity addUserInfo (@RequestBody UserInfo userInfo){
        if(authService.isAuthenticated()){
            userInfoService.addUserInfoTool(authService.getActiveUser(), userInfo);
        }
        else {
            throw new NullPointerException("Not active user");
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/info")
    public UserInfo getUserInfo(){
        if(authService.isAuthenticated()){
            return userInfoService.getUserInfo(authService.getActiveUser());
        }
        else {
            throw new NullPointerException("Not active user");
        }
    }
}
