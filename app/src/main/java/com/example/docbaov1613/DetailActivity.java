package com.example.docbaov1613;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView, contentTextView, categoryTextView, dateTextView, tagsTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ánh xạ các thành phần giao diện
        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        dateTextView = findViewById(R.id.dateTextView);
        tagsTextView = findViewById(R.id.tagsTextView);
        imageView = findViewById(R.id.imageView);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            int articleId = intent.getIntExtra("article_id", -1); // Giá trị mặc định là -1 nếu không tìm thấy
            String title = intent.getStringExtra("article_title");
            String content = intent.getStringExtra("article_content");
            String category = intent.getStringExtra("article_category");
            String date = intent.getStringExtra("article_date");
            String tags = intent.getStringExtra("article_tags");
            String imageUrl = intent.getStringExtra("article_image_url");

            // Hiển thị dữ liệu lên giao diện
            titleTextView.setText(title);
            contentTextView.setText(content);
            categoryTextView.setText("Category: " + category);
            dateTextView.setText("Publication Date: " + date);
            tagsTextView.setText("Tags: " + tags);

            // In ra URL hình ảnh để kiểm tra
            Log.d("DetailActivity", "Image URL: " + imageUrl);

            // Load hình ảnh nếu có
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Glide.with(this)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image) // Đặt placeholder
                        .error(R.drawable.error_image) // Đặt hình ảnh lỗi
                        .into(imageView);
            }
        }
    }
}