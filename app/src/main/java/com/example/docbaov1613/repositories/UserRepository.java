package com.example.docbaov1613.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.User.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService apiService;

    public UserRepository() {
        apiService = ApiClient.getClient().create(ApiService.class); // Thay thế ApiClient bằng lớp Retrofit của bạn
    }

    public LiveData<User> getUserLiveData(int userId) {
        MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
        apiService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                } else {
                    // Xử lý khi không thành công
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Xử lý khi có lỗi
            }
        });
        return mutableLiveData;
    }
}