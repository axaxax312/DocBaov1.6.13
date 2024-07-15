package com.example.docbaov1613.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.docbaov1613.Model.Category;
import com.example.docbaov1613.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        }

        Category category = categories.get(position);

        TextView categoryIdTextView = convertView.findViewById(R.id.categoryIdTextView);
        TextView categoryNameTextView = convertView.findViewById(R.id.categoryNameTextView);

        categoryIdTextView.setText(String.valueOf(category.getCategoryId()));
        categoryNameTextView.setText(category.getCategoryName());

        return convertView;
    }
}