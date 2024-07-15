package com.example.docbaov1613.Art;

public class Article {
    private int articleId;
    private String title;
    private String content;
    private String category;
    private String publicationDate;
    private String tags;
    private String imageUrl; // Thêm trường này
    private int viewCount;

    public Article(int articleId) {
        this.articleId = articleId;
    }

    public Article(int articleId, String title, String content, String category, String publicationDate, String tags) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.publicationDate = publicationDate;
        this.tags = tags;
    }

    public Article(int articleId, String title, String content, String category, String publicationDate, String tags, String imageUrl) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.publicationDate = publicationDate;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }

    // Các phương thức getter và setter đã có sẵn

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}