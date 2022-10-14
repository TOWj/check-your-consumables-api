package com.nov.checkyourconsumablesapi.models;

import com.nov.checkyourconsumablesapi.models.enums.Roles;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Login shouldn't be empty")
    @Size(min = 3, max = 100, message = "Login should be between 3 and 100 characters")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 3, max = 100, message = "Password should be between 3 and 100 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "ownerUser")
    private List<Consumables> consumablesList;

    public UserInfo() {
    }

    public UserInfo(String login) {
        this.login = login;
    }

    public UserInfo(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public List<Consumables> getConsumablesList() {
        return consumablesList;
    }

    public void setConsumablesList(List<Consumables> consumablesList) {
        this.consumablesList = consumablesList;
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

    public void setRole(Roles role) {
        this.role = role.toString();
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
