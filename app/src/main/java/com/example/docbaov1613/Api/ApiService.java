package com.example.docbaov1613.Api;

import com.example.docbaov1613.Art.Article;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("mobile_login.php")
    Call<Map<String, Object>> loginUser(@Body Map<String, String> body);
    @POST("mobile_register.php")
    Call<Map<String, String>> registerUser(@Body Map<String, String> body);
    @GET("get_articles.php")
    Call<List<Article>> getArticle();
}