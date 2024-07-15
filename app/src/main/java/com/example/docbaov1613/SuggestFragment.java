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

public class SuggestFragment extends Fragment {

    private ArticleViewModel articleViewModel;
    private ArticleAdapter articleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_suggest);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        articleAdapter = new ArticleAdapter(requireContext());
        recyclerView.setAdapter(articleAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("article_category")) {
            String category = bundle.getString("article_category");

            articleViewModel = new ViewModelProvider(requireActivity()).get(ArticleViewModel.class);
            articleViewModel.getSuggestedArticles(category).observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    articleAdapter.setArticles(articles);
                }
            });
        }
    }
}