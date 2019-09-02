package com.example.leavemanager.models;

public class LoginModel {
    String domain,username,password;

    public LoginModel(String domain, String username, String password) {
        this.domain = domain;
        this.username = username;
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
