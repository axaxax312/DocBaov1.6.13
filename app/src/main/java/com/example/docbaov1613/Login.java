package com.example.docbaov1613;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister, buttonForget;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonForget = findViewById(R.id.buttonForget);

        apiService = ApiClient.getApiService();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegister();
            }
        });

        buttonForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Forget password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);

        Call<Map<String, Object>> call = apiService.loginUser(requestBody);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                handleLoginResponse(response);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(Login.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginResponse(Response<Map<String, Object>> response) {
        if (response.isSuccessful() && response.body() != null) {
            Map<String, Object> result = response.body();
            boolean success = result.get("success") != null && (boolean) result.get("success");
            String message = result.get("message") != null ? (String) result.get("message") : "Unknown error";

            if (success) {
                Object userIdObject = result.get("userId");
                int userId = userIdObject instanceof Integer ? (int) userIdObject : (int) Math.round((double) userIdObject);
                saveUserId(userId); // Lưu userId vào SharedPreferences

                navigateToMainActivity();
            } else {
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Login.this, "Request failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void navigateToMainActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToRegister() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }
    private void saveUserId(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserId", userId);
        editor.apply();
    }
}
