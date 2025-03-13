package com.example.Good.films.models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    int id;
    @Column(name = "username", unique = true, nullable = false)
    String username;
    @Column (name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "password", unique = false, nullable = false)
    String password;

    @Column(name = "roles", unique = false, nullable = false)
    private String roles;
    public void setId(int id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRoles(String roles){
        this.roles = roles;
    }
    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRoles(){
        return this.roles;
    }
}
