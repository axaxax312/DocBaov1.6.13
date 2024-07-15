package com.example.docbaov1613;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etPreferences, etFullName,  etAddress;
    private Button btnRegister, btnBack;
    private ApiService apiService;
    private TextView tvDateOfBirth;
    private Button btnSelectDateOfBirth;
    private SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private SimpleDateFormat sdfDatabase = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPreferences = findViewById(R.id.etRePassword);
        etFullName = findViewById(R.id.etFullName);

        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        btnSelectDateOfBirth = findViewById(R.id.btnSelectDateOfBirth);
        etAddress = findViewById(R.id.etAddress);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        apiService = ApiClient.getApiService();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btnSelectDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String preferences = etPreferences.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        // Kiểm tra các trường có được điền đầy đủ không
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || preferences.isEmpty() ||
                fullName.isEmpty() || tvDateOfBirth.getText().toString().isEmpty() || address.isEmpty()) {
            Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(preferences)) {
            Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Định dạng ngày tháng năm theo định dạng để gửi lên server
        Calendar selectedCalendar = Calendar.getInstance(); // Khai báo ở đây
        String formattedDateDatabase = sdfDatabase.format(selectedCalendar.getTime());

        // Gửi dữ liệu lên server
        Map<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("password", password);
        user.put("fullName", fullName);
        user.put("dateOfBirth", formattedDateDatabase); // Sử dụng định dạng này
        user.put("address", address);

        apiService.registerUser(user).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().get("message");
                    boolean success = Boolean.parseBoolean(response.body().get("success"));
                    if (success) {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(Register.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Register.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Tạo một đối tượng Calendar từ ngày tháng năm đã chọn
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, month, dayOfMonth);

                        // Định dạng ngày tháng năm theo định dạng để hiển thị trên giao diện
                        String formattedDateDisplay = sdfDisplay.format(selectedCalendar.getTime());

                        // Định dạng ngày tháng năm theo định dạng để gửi lên server
                        String formattedDateDatabase = sdfDatabase.format(selectedCalendar.getTime());

                        // Cập nhật TextView hiển thị trên giao diện
                        tvDateOfBirth.setText(formattedDateDisplay);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}