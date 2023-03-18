package com.nhom1_ptqlyc.quizzapp.objects;

public class User {
    boolean isAdmin;
    String username;
    String password;
    String avatar;
    String birthday;
    String gender;

    public User() {
    }

    public User(boolean isAdmin, String username, String password, String avatar, String birthday, String gender) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User(boolean isAdmin, String username, String password) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
    }

    public User(boolean isAdmin, String username, String password, String birthday, String gender) {
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
