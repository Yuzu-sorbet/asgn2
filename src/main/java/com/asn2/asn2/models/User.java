package com.asn2.asn2.models;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class User {
    //serial uid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String name;
    private String password;
    private String email;
    private String haircolor;
    private int weight;
    private int height;
    private int gpa;

    //contructors below
    public User() {
    }
    public User(String name, String password, String email, String haircolor, int weight, int height, int gpa) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.haircolor = haircolor;
        this.weight = weight;
        this.height = height;
        this.gpa = gpa;
        
    }

    // allow users to edit attributes
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHaircolor() {
        return haircolor;
    }
    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getGpa() {
        return gpa;
    }
    public void setGpa(int gpa) {
        this.gpa = gpa;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    
 
}
