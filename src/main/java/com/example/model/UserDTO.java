package com.example.model;

import java.util.Set;

public class UserDTO {
    private int id;
    private String username;
    private Set<Role> roles;


    public UserDTO(int id, String username,Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
