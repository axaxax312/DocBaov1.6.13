package com.example.docbaov1613.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.docbaov1613.Api.ApiClient;
import com.example.docbaov1613.Api.ApiService;
import com.example.docbaov1613.Art.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articles;
    private MutableLiveData<List<Article>> searchResults;
    private MutableLiveData<List<Article>> featuredArticles;
    private MutableLiveData<List<Article>> latestArticles;
    private MutableLiveData<List<Article>> suggestedArticles;
    private ApiService apiService;


    public ArticleViewModel() {
        apiService = ApiClient.getApiService();
    }

    public LiveData<List<Article>> getArticles() {
        if (articles == null) {
            articles = new MutableLiveData<>();
            loadArticles();
        }
        return articles;
    }

    public LiveData<List<Article>> getSearchResults() {
        if (searchResults == null) {
            searchResults = new MutableLiveData<>();
        }
        return searchResults;
    }

    public LiveData<List<Article>> getFeaturedArticles() {
        if (featuredArticles == null) {
            featuredArticles = new MutableLiveData<>();
            loadFeaturedArticles();
        }
        return featuredArticles;
    }

    public LiveData<List<Article>> getLatestArticles() {
        if (latestArticles == null) {
            latestArticles = new MutableLiveData<>();
            loadLatestArticles();
        }
        return latestArticles;
    }

    public LiveData<List<Article>> getSuggestedArticles(String category) {
        if (suggestedArticles == null) {
            suggestedArticles = new MutableLiveData<>();
            loadSuggestedArticles(category);
        }
        return suggestedArticles;
    }

    public void loadSuggestedArticles(String category) {
        Call<List<Article>> call = apiService.getSuggestedArticles(category);

        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    suggestedArticles.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void loadArticles() {
        apiService.getArticles().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articles.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadFeaturedArticles() {
        apiService.getFeaturedArticles().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    featuredArticles.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadLatestArticles() {
        apiService.getLastestArticles().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    latestArticles.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void clearSearchResults() {
        searchResults.postValue(new ArrayList<>());
    }

    public void searchArticles(String query) {
        apiService.searchArticles(query).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchResults.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}