package com.kach.studyhelperback.Controllers;

import com.kach.studyhelperback.Models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    User[] users = {
            new User(3, "AlexandrovAn", "Андрей Александров"),
            new User(1, "kachmazoff", "Александр Качамзов"),
            new User(4, "Wampussik", "Владимир Ротов"),
            new User(2, "anri217", "Андрей Рязанов")
    };

    @GetMapping("/users")
    public User[] getUsers() {
        return users;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        if (id - 1 < 0 || id > users.length) {
            /// TODO: ERROR handling using ApiError class
        }
        return users[id - 1];
    }
}
