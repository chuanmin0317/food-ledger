package com.example.foodledger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodledger.R;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        super(R.layout.today);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button newMealButton = view.findViewById(R.id.fl_new_meal);

        newMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MealActivity.class);
            startActivity(intent);
        });
    }
}
