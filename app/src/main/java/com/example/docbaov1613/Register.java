package com.example.docbaov1613;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.docbaov1613.Api.ApiService;

import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etPreferences;
    private Button btnRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo các view
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPreferences = findViewById(R.id.etPreferences);
        btnRegister = findViewById(R.id.btnRegister);

        // Khởi tạo Retrofit để kết nối API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.116/news_platform/") // Địa chỉ của server
                .addConverterFactory(GsonConverterFactory.create()) // Converter để chuyển đổi JSON
                .build();

        // Tạo instance của ApiService
        apiService = retrofit.create(ApiService.class);

        // Thiết lập sự kiện click cho nút đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    // Hàm để đăng ký người dùng
    private void registerUser() {
        // Lấy dữ liệu từ các trường input
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String preferences = etPreferences.getText().toString().trim();

        // Kiểm tra xem các trường có rỗng không
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || preferences.isEmpty()) {
            Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo map chứa dữ liệu người dùng để gửi lên server
        Map<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("password", password);
        user.put("preferences", preferences);

        // Gọi API để đăng ký người dùng
        apiService.registerUser(user).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Nhận phản hồi từ server
                    String message = response.body().get("message");
                    boolean success = Boolean.parseBoolean(response.body().get("success"));
                    if (success) {
                        // Đăng ký thành công
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                        finish(); // Kết thúc activity sau khi đăng ký thành công
                    } else {
                        // Đăng ký thất bại
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Lỗi kết nối
                    Toast.makeText(Register.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                // Xử lý lỗi khi không thể kết nối tới server
                Toast.makeText(Register.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}