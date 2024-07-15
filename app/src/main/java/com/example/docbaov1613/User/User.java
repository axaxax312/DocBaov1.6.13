package com.example.docbaov1613.User;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("UserID")
    private int userId;

    @SerializedName("Username")
    private String username;

    @SerializedName("Email")
    private String email;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("DateOfBirth")
    private String dateOfBirth;

    @SerializedName("Address")
    private String address;

    @SerializedName("AvatarUrl")
    private String avatarUrl;

    public User(int userId, String username, String email, String fullName, String dateOfBirth, String address, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}