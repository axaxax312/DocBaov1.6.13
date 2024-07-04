package com.example.docbaov1613;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("UserID")
    private int userId;

    @SerializedName("Username")
    private String username;

    @SerializedName("Role")
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}