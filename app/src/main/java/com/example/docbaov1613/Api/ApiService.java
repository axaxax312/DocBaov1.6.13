package com.example.docbaov1613.Api;

import com.example.docbaov1613.Art.Article;
import com.example.docbaov1613.Model.Comment;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("mobile_login.php")
    Call<Map<String, Object>> loginUser(@Body Map<String, String> body);

    @POST("mobile_register.php")
    Call<Map<String, String>> registerUser(@Body Map<String, String> body);

    @GET("get_articles.php")
    Call<List<Article>> getArticles();

    @FormUrlEncoded
    @POST("update_view_count.php")
    Call<Void> updateViewCount(@Field("articleId") int articleId);

    @FormUrlEncoded
    @POST("submit_comment.php")
    Call<Void> submitComment(@Field("articleId") int articleId, @Field("userId") int userId, @Field("commentText") String commentText);

    @GET("get_comments.php")
    Call<List<Comment>> getComments(@Query("articleId") int articleId);

}