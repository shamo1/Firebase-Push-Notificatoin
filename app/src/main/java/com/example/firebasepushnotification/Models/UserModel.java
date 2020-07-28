package com.example.firebasepushnotification.Models;

public class UserModel {
    String key;

    public UserModel() {
    }

    public UserModel(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
