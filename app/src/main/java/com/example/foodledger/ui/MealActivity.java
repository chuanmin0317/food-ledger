package com.example.foodledger.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.view.View;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodledger.R;
import com.example.foodledger.model.FoodItemDraft;
import com.example.foodledger.model.MealDraft;
import com.example.foodledger.model.MealType;

import java.time.LocalDate;

public class MealActivity extends AppCompatActivity {
    private EditText nameInput, mealUnit, mealServings;
    private Button mealDateButton;
    private LocalDate selectedDate;
    private EditText kcal, protein, fat, carbs, price;
    private TextView total;
    private Spinner mealType;
    private Button saveButton, deleteButton, addItemButton;
    private LinearLayout itemsContainer;
    private MealDraft currentDraft;
    private final MealType[] mealTypes = {
            MealType.BREAKFAST,
            MealType.LUNCH,
            MealType.DINNER,
            MealType.SNACK
    };

    private Double readOptionalDouble(EditText input) {
        input.setError(null);
        String text = input.getText().toString().trim();

        try {
            return text.isEmpty() ? null : Double.valueOf(text);
        } catch (NumberFormatException e) {
            input.setError("請輸入有效數字");
            throw e;
        }
    }

    private Long readOptionalLong(EditText input) {
        input.setError(null);
        String text = input.getText().toString().trim();

        try {
            return text.isEmpty() ? null : Long.valueOf(text);
        } catch (NumberFormatException e) {
            input.setError("請輸入整數金額");
            throw e;
        }
    }

    private FoodItemDraft collectFoodItemDraft() {
        FoodItemDraft item = new FoodItemDraft();
        item.setName(nameInput.getText().toString().trim());
        item.setUnitDescription(mealUnit.getText().toString().trim());
        item.setConsumedUnits(readOptionalDouble(mealServings));
        item.setCaloriesKcal(readOptionalDouble(kcal));
        item.setProteinG(readOptionalDouble(protein));
        item.setFatG(readOptionalDouble(fat));
        item.setCarbsG(readOptionalDouble(carbs));

        return item;
    }
    private void collectMealDraft(MealDraft draft) {
        draft.setType(mealTypes[mealType.getSelectedItemPosition()]);
        draft.setEatenAt(
                selectedDate.atTime(draft.getEatenAt().toLocalTime())
        );
        draft.setTotalCost(readOptionalLong(price));
    }

    private void clearFoodInputs() {
        EditText[] inputs = {
                nameInput, mealUnit, mealServings,
                kcal, protein, fat, carbs
        };

        for (EditText input : inputs) {
            input.setText("");
            input.setError(null);
        }
        nameInput.requestFocus();
    }

    private void renderItems() {
        itemsContainer.removeAllViews();

        for (FoodItemDraft item : currentDraft.getItems()) {
            View itemRow = getLayoutInflater().inflate(
                    R.layout.food_item_row, itemsContainer, false
            );

            TextView nameText = itemRow.findViewById(R.id.fl_item_name);
            TextView unitsText = itemRow.findViewById(R.id.fl_item_units);
            Button removeButton = itemRow.findViewById(R.id.fl_remove_item);

            nameText.setText(item.getName());

            String units = item.getConsumedUnits() == null
                    ? "份數未填"
                    : item.getConsumedUnits() + "份";
            unitsText.setText(units);

            removeButton.setOnClickListener(v -> {
                currentDraft.getItems().remove(item);
                renderItems();
            });

            itemsContainer.addView(itemRow);
        }
    }

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
        itemsContainer = findViewById(R.id.fl_items);
        currentDraft = new MealDraft();
        mealDateButton = findViewById(R.id.fl_meal_date);
        selectedDate = currentDraft.getEatenAt().toLocalDate();
        mealDateButton.setText(selectedDate.toString());

        String[] labels = {"早餐", "午餐", "晚餐", "點心"};
        mealType = findViewById(R.id.fl_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, labels
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(adapter);

        saveButton = findViewById(R.id.fl_save);
        deleteButton = findViewById(R.id.fl_delete);
        addItemButton = findViewById(R.id.fl_add_item);

        mealDateButton.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    (view, year, month, day) -> {
                        selectedDate = LocalDate.of(year, month + 1, day);
                        mealDateButton.setText(selectedDate.toString());
                    },
                    selectedDate.getYear(),
                    selectedDate.getMonthValue() - 1,
                    selectedDate.getDayOfMonth()
            );
            dialog.show();
        });

        addItemButton.setOnClickListener(v -> {
            try {
                FoodItemDraft item = collectFoodItemDraft();
                currentDraft.getItems().add(item);
                renderItems();
                clearFoodInputs();

                Log.d("MealActivity",
                        "items=" + currentDraft.getItems().size());
            } catch (NumberFormatException e) {
                return;
            }
        });

//        deleteItemButton.setOnClickListener(v -> {
//            currentDraft.getItems().remove(item);
//            renderItems();
//        });

        saveButton.setOnClickListener(v -> {
            try {
                collectMealDraft(currentDraft);
                Log.d("MealActivity", "eatenAt=" + currentDraft.getEatenAt());
            } catch (NumberFormatException e) {
                return;
            }
        });
    }


}