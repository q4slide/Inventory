package com.inventory.inventory.businesslayer.entity;


import jakarta.persistence.*;

/**
 * @author TomaVasilev
 * @version 1.0
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean admin;


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.admin = isAdmin;
    }

    public User(long id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = isAdmin;
    }


    public User() {
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
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

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + admin +
                '}';
    }
}
