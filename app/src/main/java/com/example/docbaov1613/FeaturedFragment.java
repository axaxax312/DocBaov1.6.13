package com.example.docbaov1613;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docbaov1613.Art.Article;
import com.example.docbaov1613.Art.ArticleAdapter;
import com.example.docbaov1613.ViewModels.ArticleViewModel;

import java.util.List;

public class FeaturedFragment extends Fragment {

    private ArticleViewModel articleViewModel;
    private ArticleAdapter articleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        articleAdapter = new ArticleAdapter(requireContext());
        recyclerView.setAdapter(articleAdapter);

        articleViewModel = new ViewModelProvider(requireActivity()).get(ArticleViewModel.class);
        articleViewModel.getFeaturedArticles().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleAdapter.setArticles(articles);
            }
        });

        observeSearchResults();
        // Load lại danh sách bài viết đặc trưng khi fragment được tạo
        articleViewModel.loadFeaturedArticles();
        return view;
    }

    private void observeSearchResults() {
        articleViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleAdapter.setArticles(articles);
            }
        });
    }

    public void reloadArticles() {
        articleViewModel.loadFeaturedArticles();
    }

    public void searchArticles(String query) {
        articleViewModel.searchArticles(query);
    }
}