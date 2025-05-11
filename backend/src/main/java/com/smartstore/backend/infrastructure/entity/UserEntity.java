package com.smartstore.backend.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "picture", nullable = true, columnDefinition = "TEXT")
    private String picture;

    public UserEntity() {}

    public UserEntity(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPicture() { return picture; }

    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPicture(String picture) { this.picture = picture; }
}


