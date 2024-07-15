package com.example.docbaov1613;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docbaov1613.Adapter.NotificationAdapter;
import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.Model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NotificationAdapter(getContext());
        recyclerView.setAdapter(adapter);
        loadNotifications();
        return root;
    }

    private void loadNotifications() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Notification>> call = apiService.getNotifications();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Notification> notifications = response.body();
                    adapter.setNotifications(notifications);

                    // Log data received
                    Log.d("NotificationFragment", "Received notifications: " + notifications.size());
                } else {
                    Log.e("NotificationFragment", "Failed to fetch notifications. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                // Handle failure case
                t.printStackTrace();
                Log.e("NotificationFragment", "Failed to fetch notifications: " + t.getMessage());
            }
        });
    }
}