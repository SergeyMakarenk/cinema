package com.makarenko.main.model;

public class Person {
    private Integer id;
    private String username;
    private byte[] password;
    private int age;
    private String role;
    private byte[] salt;

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
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

    public Person(String username, byte[] password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public Person(Integer id, String username, byte[] password, int age, String role, byte[] salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
        this.salt = salt;
    }

    public Person(String username, byte[] password, int age, String role, byte[] salt) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
        this.salt = salt;
    }
}
