package com.example.docbaov1613.Art;

public class Article {
    private int articleId;
    private String title;
    private String content;
    private String category;
    private String publicationDate;
    private String tags;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl; // Thêm trường này

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

    // Getter và Setter cho articleId
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    // Getter và Setter cho title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter và Setter cho content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter và Setter cho category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter và Setter cho publicationDate
    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Getter và Setter cho tags
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}