package com.example.docbaov1613.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.docbaov1613.Model.Comment;
import com.example.docbaov1613.R;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void setComments(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentUsernameTextView.setText(comment.getUsername());
        holder.commentTimestampTextView.setText(comment.getTimestamp());
        holder.commentTextView.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView commentUsernameTextView;
        public TextView commentTimestampTextView;
        public TextView commentTextView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            commentUsernameTextView = itemView.findViewById(R.id.commentUsernameTextView);
            commentTimestampTextView = itemView.findViewById(R.id.commentTimestampTextView);
            commentTextView = itemView.findViewById(R.id.commentTextView);
        }
    }
}