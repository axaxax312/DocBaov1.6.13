package com.example.docbaov1613.Model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("CommentID")
    private int commentId;

    @SerializedName("ArticleID")
    private int articleId;

    @SerializedName("UserID")
    private int userId;

    @SerializedName("Content")
    private String content;

    @SerializedName("Username")
    private String username;

    @SerializedName("Timestamp")
    private String timestamp;

    // Constructors, getters and setters
    public Comment(int commentId, int articleId, int userId, String content, String username, String timestamp) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}