package com.example.foodledger.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodledger.R;

public class MealActivity extends AppCompatActivity {
    private EditText nameInput, mealUnit, mealServings;
    private EditText kcal, protein, fat, carbs, price;
    private TextView total;
    private Spinner mealType;
    private Button saveButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        nameInput = findViewById(R.id.fl_name);
        mealUnit = findViewById(R.id.fl_unit);
        mealServings = findViewById(R.id.fl_servings);
        kcal = findViewById(R.id.fl_kcal);
        protein =findViewById(R.id.fl_p);
        fat = findViewById(R.id.fl_f);
        carbs = findViewById(R.id.fl_c);
        price = findViewById(R.id.fl_price);
        total = findViewById(R.id.fl_editor_total);

        mealType = findViewById(R.id.fl_type);

        saveButton = findViewById(R.id.fl_save);
        deleteButton = findViewById(R.id.fl_delete);

        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            Log.d("name:$s", name);
        });
    }


}