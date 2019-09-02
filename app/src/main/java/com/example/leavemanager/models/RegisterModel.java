package com.example.leavemanager.models;

public class RegisterModel {
    String domain,username,name,email;
    public RegisterModel(String domain, String name, String username, String email) {
        this.domain = domain;
        this.username = username;
        this.name = name;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
