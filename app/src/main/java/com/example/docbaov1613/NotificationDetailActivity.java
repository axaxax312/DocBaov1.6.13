package com.example.docbaov1613;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationDetailActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewMessage, textViewCreatedAt;
    private Button buttonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewMessage = findViewById(R.id.textViewMessage);
        textViewCreatedAt = findViewById(R.id.textViewCreatedAt);
        buttonClose = findViewById(R.id.buttonClose);

        // Get notification details from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String message = extras.getString("message");
            String createdAt = extras.getString("created_at");

            if (title != null) {
                textViewTitle.setText(title);
            }
            if (message != null) {
                textViewMessage.setText(message);
            }
            if (createdAt != null && !createdAt.isEmpty()) {
                // Format createdAt date for display
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                try {
                    Date date = inputFormat.parse(createdAt);
                    String formattedDate = outputFormat.format(date);
                    textViewCreatedAt.setText(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    textViewCreatedAt.setText(createdAt);
                }
            }
        }

        buttonClose.setOnClickListener(v -> finish());
    }
}