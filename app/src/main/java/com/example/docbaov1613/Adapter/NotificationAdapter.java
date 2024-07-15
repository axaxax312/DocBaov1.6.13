package com.example.docbaov1613.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docbaov1613.Model.Notification;
import com.example.docbaov1613.NotificationDetailActivity;
import com.example.docbaov1613.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context context;
    private List<Notification> notifications;

    public NotificationAdapter(Context context) {
        this.context = context;
        this.notifications = new ArrayList<>();
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications.clear();
        this.notifications.addAll(notifications);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        final Notification notification = notifications.get(position);
        holder.textViewTitle.setText(notification.getTitle());
        holder.textViewMessage.setText(notification.getMessage());

        // Check if createdAt is not null and not empty and is in the correct format
        if (isValidDateFormat(notification.getCreatedAt())) {
            String formattedDate = formatDate(notification.getCreatedAt());
            holder.textViewTime.setText(formattedDate);
        } else {
            // Handle case where createdAt is null, empty, or invalid format
            holder.textViewTime.setText("Invalid date format");
        }

        // Set OnClickListener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event here, for example, open a detail view
                // Example: Intent to open detail activity with notification details
                Intent intent = new Intent(context, NotificationDetailActivity.class);
                intent.putExtra("title", notification.getTitle());
                intent.putExtra("message", notification.getMessage());
                intent.putExtra("created_at", notification.getCreatedAt());
                context.startActivity(intent);
            }
        });
    }

    private boolean isValidDateFormat(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        dateFormat.setLenient(false); // Set lenient to false to strictly check the format

        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewMessage, textViewTime;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }

    private String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return "";
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = inputFormat.parse(dateString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString; // In case parsing fails, set the original string
        }
    }
}