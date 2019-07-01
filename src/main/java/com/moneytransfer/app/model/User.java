package com.moneytransfer.app.model;

public class User {
    private String userName;
    private String name;
    private int age;
    private int bankAmount;

    public User(String userName, String name, int age, int bankAmount) {
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.bankAmount = bankAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(int bankAmount) {
        this.bankAmount = bankAmount;
    }

    @Override
    public String toString() {
        return this.name + " " + this.age;
    }
}
