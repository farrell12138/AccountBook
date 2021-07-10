package com.example.accountapp;

public class User {
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public User(){}

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int id;
    private String userName;
    private String password;

}
