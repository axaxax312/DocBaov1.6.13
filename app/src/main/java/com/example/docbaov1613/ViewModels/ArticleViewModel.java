package com.example.docbaov1613.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.docbaov1613.Art.Article;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArticleViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articles;
    private MutableLiveData<List<Article>> searchResults;
    private MutableLiveData<List<Article>> featuredArticles;
    private MutableLiveData<List<Article>> latestArticles;

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

    public void loadArticles() {
        new Thread(() -> {
            try {
                URL url = new URL("http://192.168.1.6/news_platform/get_articles.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                JSONArray jsonArray = new JSONArray(content.toString());
                List<Article> articleList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Article article = new Article(
                            jsonObject.getInt("articleId"),
                            jsonObject.getString("title"),
                            jsonObject.getString("content"),
                            jsonObject.getString("category"),
                            jsonObject.getString("publicationDate"),
                            jsonObject.getString("tags"),
                            jsonObject.getString("imageUrl")
                    );
                    articleList.add(article);
                }

                articles.postValue(articleList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void loadFeaturedArticles() {
        new Thread(() -> {
            try {
                URL url = new URL("http://192.168.1.6/news_platform/get_featured_articles.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                JSONArray jsonArray = new JSONArray(content.toString());
                List<Article> articleList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Article article = new Article(
                            jsonObject.getInt("articleId"),
                            jsonObject.getString("title"),
                            jsonObject.getString("content"),
                            jsonObject.getString("category"),
                            jsonObject.getString("publicationDate"),
                            jsonObject.getString("tags"),
                            jsonObject.getString("imageUrl")
                    );
                    articleList.add(article);
                }

                featuredArticles.postValue(articleList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void loadLatestArticles() {
        new Thread(() -> {
            try {
                URL url = new URL("http://192.168.1.6/news_platform/get_latest_articles.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                JSONArray jsonArray = new JSONArray(content.toString());
                List<Article> articleList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Article article = new Article(
                            jsonObject.getInt("articleId"),
                            jsonObject.getString("title"),
                            jsonObject.getString("content"),
                            jsonObject.getString("category"),
                            jsonObject.getString("publicationDate"),
                            jsonObject.getString("tags"),
                            jsonObject.getString("imageUrl")
                    );
                    articleList.add(article);
                }

                latestArticles.postValue(articleList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void clearSearchResults() {
        searchResults.postValue(new ArrayList<>());
    }
    public void searchArticles(String query) {
        new Thread(() -> {
            try {
                URL url = new URL("http://192.168.1.6/news_platform/get_articles.php?search=" + query);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                JSONArray jsonArray = new JSONArray(content.toString());
                List<Article> articleList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Article article = new Article(
                            jsonObject.getInt("articleId"),
                            jsonObject.getString("title"),
                            jsonObject.getString("content"),
                            jsonObject.getString("category"),
                            jsonObject.getString("publicationDate"),
                            jsonObject.getString("tags"),
                            jsonObject.getString("imageUrl")
                    );
                    articleList.add(article);
                }

                searchResults.postValue(articleList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

//    public LiveData<Article> getArticleById(int articleId) {
//        MutableLiveData<Article> articleLiveData = new MutableLiveData<>();
//
//        // Thực hiện truy vấn cơ sở dữ liệu hoặc API để lấy dữ liệu bài viết dựa trên articleId
//        new Thread(() -> {
//            try {
//                URL url = new URL("http://192.168.1.7/news_platform/get_article_by_id.php?articleId=" + articleId);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder content = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    content.append(inputLine);
//                }
//                in.close();
//                connection.disconnect();
//
//                JSONObject jsonObject = new JSONObject(content.toString());
//                Article article = new Article(
//                        jsonObject.getInt("articleId"),
//                        jsonObject.getString("title"),
//                        jsonObject.getString("content"),
//                        jsonObject.getString("category"),
//                        jsonObject.getString("publicationDate"),
//                        jsonObject.getString("tags"),
//                        jsonObject.getString("imageUrl")
//                );
//
//                articleLiveData.postValue(article);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        return articleLiveData;
//    }
