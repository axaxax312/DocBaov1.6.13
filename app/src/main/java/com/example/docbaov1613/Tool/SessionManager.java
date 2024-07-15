package com.example.docbaov1613.Tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static SessionManager instance;
    private SharedPreferences sharedPreferences;

    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public int getUserId() {
        return sharedPreferences.getInt("userID", -1); // Mặc định trả về -1 nếu không có userID
    }

    public void setUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userID", userId);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString("username", "");
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public String getRole() {
        return sharedPreferences.getString("role", "");
    }

    public void setRole(String role) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("role", role);
        editor.apply();
    }
    public String getCategory() {
        return sharedPreferences.getString("category", "");
    }

    public void setCategory(String category) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("category", category);
        editor.apply();
    }
}