package com.example.docbaov1613.Model;

public class Comment {
    private int CommentID;
    private int ArticleID;
    private int UserID;
    private String Content;
    private String Timestamp;

    // Constructor, getters, and setters
    public Comment(int commentID, int articleID, int userID, String content, String timestamp) {
        CommentID = commentID;
        ArticleID = articleID;
        UserID = userID;
        Content = content;
        Timestamp = timestamp;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getArticleID() {
        return ArticleID;
    }

    public void setArticleID(int articleID) {
        ArticleID = articleID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}