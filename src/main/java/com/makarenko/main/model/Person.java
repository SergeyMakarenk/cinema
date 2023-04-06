package com.makarenko.main.model;

public class Person {
    private Integer id;
    private String username;
    private String password;
    private int age;
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public Person(Integer id, String username, String password, int age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public Person(String username, String password, int age, String role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }
}
