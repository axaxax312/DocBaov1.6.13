package com.example.docbaov1613;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.Model.Category;
import com.example.docbaov1613.Adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {

    private ListView listViewCategories;
    private CategoryAdapter adapter;
    private List<Category> categoryList = new ArrayList<>();

    private ApiService apiService = ApiClient.getApiService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_library, container, false);

        listViewCategories = rootView.findViewById(R.id.listViewCategories);

        // Khởi tạo adapter cho ListView
        adapter = new CategoryAdapter(getContext(), categoryList);
        listViewCategories.setAdapter(adapter);

        // Gọi API để lấy danh sách categories
        getCategories();

        return rootView;
    }

    private void getCategories() {
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    categoryList.clear();
                    categoryList.addAll(categories);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to fetch categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Network error! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}