package com.smartstore.backend.domain.model;


public class User {
    private final String email;
    private final String name;
    private final String picture;

    public User(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPicture() { return picture; }
}
