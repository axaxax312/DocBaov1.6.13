package com.example.docbaov1613.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.User.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource {

    private ApiService apiService;

    public UserDataSource() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public LiveData<User> getUserLiveData(int userId) {
        MutableLiveData<User> userData = new MutableLiveData<>();

        // Gọi API để lấy dữ liệu người dùng theo userId
        apiService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userData.setValue(null);
            }
        });

        return userData;
    }
}