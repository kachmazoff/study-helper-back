package com.kach.studyhelperback.Domain;

import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
