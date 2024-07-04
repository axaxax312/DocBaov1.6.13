package com.example.docbaov1613;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docbaov1613.Api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Khởi tạo Retrofit và ApiService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.116/news_platform/") // URL của mobile_login.php
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Tạo object body để gửi lên server
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("username", username);
                requestBody.put("password", password);

                // Gọi phương thức loginUser từ ApiService
                Call<Map<String, Object>> call = apiService.loginUser(requestBody);
                call.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        if (response.isSuccessful()) {
                            Map<String, Object> result = response.body();
                            boolean success = (boolean) result.get("success");
                            String message = (String) result.get("message");

                            if (success) {
                                // Đăng nhập thành công, chuyển sang MainActivity
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Đăng nhập thất bại, hiển thị thông báo lỗi
                                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Đã có lỗi xảy ra
                            Toast.makeText(Login.this, "Request failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                        // Xử lý khi có lỗi trong quá trình gọi API
                        Toast.makeText(Login.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}