package com.wind.Gentleman.domain;

import java.util.Date;

public class User {
    private int id;
    private String rename;
    private String email;
    private String password;
    private int balance;
    private Date date;

    public User(int id, String rename, String email, String password, int balance, Date date) {
        this.id = id;
        this.rename = rename;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.date = date;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rename='" + rename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRename() {
        return rename;
    }

    public void setRename(String rename) {
        this.rename = rename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
