package com.kach.studyhelperback.Models;

public class User {
    private int id;
    private String login;
    private String password;
    private String displayName;

    public User() {
    }

    public User(int id, String login, String displayName) {
        this.id = id;
        this.login = login;
        this.displayName = displayName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
