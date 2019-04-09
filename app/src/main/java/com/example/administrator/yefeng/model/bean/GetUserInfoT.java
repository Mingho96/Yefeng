package com.example.administrator.yefeng.model.bean;

public class GetUserInfoT {
    private int id;
    private String username;
    private String nickname;
    private String token;
    private String email;
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "GetUserInfoT{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", nickName='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
