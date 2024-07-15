package com.example.docbaov1613.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.docbaov1613.User.User;
import com.example.docbaov1613.repositories.UserRepository;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private LiveData<User> userLiveData;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public void init(int userId) {
        userLiveData = userRepository.getUserLiveData(userId);
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}