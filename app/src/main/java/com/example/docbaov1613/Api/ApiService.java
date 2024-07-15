package com.example.docbaov1613.Api;

import com.example.docbaov1613.Art.Article;
import com.example.docbaov1613.Model.Category;
import com.example.docbaov1613.Model.Comment;
import com.example.docbaov1613.Model.Notification;
import com.example.docbaov1613.User.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("mobile_login.php")
    Call<Map<String, Object>> loginUser(@Body Map<String, String> body);

    @POST("mobile_register.php")
    Call<Map<String, String>> registerUser(@Body Map<String, String> body);

    @GET("get_articles.php")
    Call<List<Article>> getArticles();

    @GET("get_articles.php")
    Call<List<Article>> searchArticles(@Query("search") String query);

    @GET("get_featured_articles.php")
    Call<List<Article>> getFeaturedArticles();

    @GET("get_latest_articles.php")
    Call<List<Article>> getLastestArticles();

    @GET("get_articles_by_category.php")
    Call<List<Article>> getSuggestedArticles(@Query("category") String category);

    @FormUrlEncoded
    @POST("update_view_count.php")
    Call<Void> updateViewCount(@Field("articleId") int articleId);

    @FormUrlEncoded
    @POST("submit_comment.php")
    Call<Void> submitComment(@Field("articleId") int articleId, @Field("userId") int userId, @Field("commentText") String commentText);

    @GET("get_comments.php")
    Call<List<Comment>> getComments(@Query("articleId") int articleId);

    @GET("get_categories.php")
    Call<List<Category>> getCategories();

    // Định nghĩa phương thức GET để lấy danh sách thông báo
    @GET("notification_endpoint.php")
    Call<List<Notification>> getNotifications();

    @FormUrlEncoded
    @POST("save_article.php")
    Call<Void> saveArticle(
            @Field("article_id") int articleId,
            @Field("user_id") int userId
    );

    @GET("mobile_get_user.php")
    Call<User> getUser(@Query("userId") int userId);
    @GET("get_user_profile/{userId}")
    Call<User> getUserProfile(@Path("userId") int userId);
    @GET("get_user_by_id.php")
    Call<User> getUserById(@Query("userId") int userId);
    @FormUrlEncoded
    @POST("upload_avatar.php")
    Call<Void> uploadAvatar(@Field("userId") int userId, @Field("base64Image") String base64Image);
}