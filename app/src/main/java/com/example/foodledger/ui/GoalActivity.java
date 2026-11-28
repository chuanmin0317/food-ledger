package com.example.foodledger.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodledger.R;

public class GoalActivity extends AppCompatActivity {
    private EditText kcalInput, proteinInput, fatInput, carbInput;
    private CheckBox kcalEnabled, proteinEnabled, fatEnabled, carbEnable;
    private Button saveGoals;
    private void bindView() {
        kcalInput = findViewById(R.id.fl_goal_kcal);
        kcalEnabled = findViewById(R.id.fl_goal_kcal_enabled);
        proteinInput = findViewById(R.id.fl_goal_p);
        proteinEnabled = findViewById(R.id.fl_goal_p_enabled);
        carbInput = findViewById(R.id.fl_goal_c);
        carbEnable = findViewById(R.id.fl_goal_c_enabled);
        fatInput = findViewById(R.id.fl_goal_f);
        fatEnabled = findViewById(R.id.fl_goal_f_enabled);
        saveGoals = findViewById(R.id.fl_save_goals);
    }

    private void setupListener() {
        kcalEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setGoalInputEnabled();
        });

        proteinEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setGoalInputEnabled();
        });

        carbEnable.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setGoalInputEnabled();
        });

        fatEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setGoalInputEnabled();
        });

        saveGoals.setOnClickListener(v -> {
            try {
                Double kcalGoal = readGoalInput(kcalEnabled, kcalInput);
                Double proteinGoal = readGoalInput(proteinEnabled, proteinInput);
                Double carbGoal = readGoalInput(carbEnable, carbInput);
                Double fatGoal = readGoalInput(fatEnabled, fatInput);

                // logcat
                android.util.Log.d("GoalActivity", "Kcal Goal: " + kcalGoal);
                android.util.Log.d("GoalActivity", "Protein Goal: " + proteinGoal);
                android.util.Log.d("GoalActivity", "Carb Goal: " + carbGoal);
                android.util.Log.d("GoalActivity", "Fat Goal: " + fatGoal);
            } catch (NumberFormatException e) {
                return;
            }
        });
    }

    private void setGoalInputEnabled() {
        kcalInput.setEnabled(kcalEnabled.isChecked());
        proteinInput.setEnabled(proteinEnabled.isChecked());
        carbInput.setEnabled(carbEnable.isChecked());
        fatInput.setEnabled(fatEnabled.isChecked());
    }

    private Double readGoalInput(CheckBox enable, EditText input) {
        input.setError(null);
        String text = input.getText().toString().trim();

        try {
            if (enable.isChecked() && text.isEmpty()) return null;
            return enable.isChecked() ? Double.valueOf(text) : null;
        } catch (NumberFormatException e) {
            input.setError("請輸入有效數字");
            throw e;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);

        bindView();

        setupListener();

        setGoalInputEnabled();

    }

    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("無法完成操作")
                .setMessage(message)
                .setPositiveButton("確定", null)
                .show();
    }

}
