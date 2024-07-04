package com.example.docbaov1613.Art;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docbaov1613.DetailActivity;
import com.example.docbaov1613.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private List<Article> articles;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
    public ArticleAdapter(Context context) {
        this.context = context;
        this.articles = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.contentTextView.setText(article.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click vào mục bài báo
                // Ví dụ: Mở một activity mới để hiển thị chi tiết bài báo
                // Intent intent = new Intent(v.getContext(), DetailActivity.class);
                // intent.putExtra("article_id", article.getId());
                // v.getContext().startActivity(intent);
                openDetailActivity(article);
                Toast.makeText(v.getContext(), "Bạn đã click vào: " + article.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void openDetailActivity(Article article) {
        // Ví dụ: Mở một activity mới để hiển thị chi tiết bài báo
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("article_id", article.getArticleId());
        intent.putExtra("article_title", article.getTitle());
        intent.putExtra("article_content", article.getContent());
        intent.putExtra("article_category", article.getCategory());
        intent.putExtra("article_date", article.getPublicationDate());
        intent.putExtra("article_tags", article.getTags());

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView contentTextView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            contentTextView = itemView.findViewById(R.id.article_content);

        }
    }
}