package com.example.docbaov1613.Model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("CategoryID")
    private int categoryId;

    @SerializedName("CategoryName")
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}