package com.example.docbaov1613;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.User.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageViewAvatar;
    private TextView textViewUsername, textViewEmail, textViewFullName, textViewAddress, textViewDateOfBirth;
    private Button btnUploadAvatar;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewFullName = view.findViewById(R.id.textViewFullName);
        textViewAddress = view.findViewById(R.id.textViewAddress);
        textViewDateOfBirth = view.findViewById(R.id.textViewDateOfBirth);
        btnUploadAvatar = view.findViewById(R.id.btnUploadAvatar);

        apiService = ApiClient.getApiService();

        loadUserInfo();

        btnUploadAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{READ_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
                } else {
                    openImageChooser();
                }
            }
        });

        return view;
    }

    private void loadUserInfo() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("UserId", -1);

        if (userId == -1) {
            Toast.makeText(getActivity(), "User ID not found", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<User> call = apiService.getUserById(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    displayUserInfo(user);
                } else {
                    Toast.makeText(getActivity(), "Failed to load user info", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayUserInfo(User user) {
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());
        textViewFullName.setText(user.getFullName());
        textViewAddress.setText(user.getAddress());
        textViewDateOfBirth.setText(user.getDateOfBirth());

        // Sử dụng Glide để load ảnh avatar
        Glide.with(this)
                .load(user.getAvatarUrl())
                .placeholder(R.drawable.default_avatar)
                .into(imageViewAvatar);
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                uploadImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(Bitmap bitmap) {
        // Convert bitmap to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("UserId", -1);

        if (userId == -1) {
            Toast.makeText(getActivity(), "User ID not found", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Void> call = apiService.uploadAvatar(userId, base64Image);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Avatar uploaded successfully", Toast.LENGTH_SHORT).show();
                    // Reload user info to update avatar
                    loadUserInfo();
                } else {
                    Toast.makeText(getActivity(), "Failed to upload avatar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}