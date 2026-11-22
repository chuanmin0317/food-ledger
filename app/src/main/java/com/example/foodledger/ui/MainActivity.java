package com.example.foodledger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodledger.R;

public class MainActivity extends AppCompatActivity {
    private Button backButton, navAdd;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        navAdd = findViewById(R.id.fl_nav_add);
        navAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, MealActivity.class);
            startActivity(intent);
        });

        title = findViewById(R.id.fl_title);
        title.setText(R.string.text_0);

        backButton = findViewById(R.id.fl_back);
        backButton.setVisibility(View.GONE);
    }
}