package com.nov.checkyourconsumablesapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Login shouldn't be empty")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Password shouldn't be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public UserInfo() {
    }

    public UserInfo(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
