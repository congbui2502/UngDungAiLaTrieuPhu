package com.example.ailatrieuphu;

public class User {
    public User(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public  String phone;
    public String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String password;







}
