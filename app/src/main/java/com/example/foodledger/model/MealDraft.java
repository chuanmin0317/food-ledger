package com.example.foodledger.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MealDraft {
    private final String existingMealId;

    private LocalDateTime eatenAt;
    private MealType type;

    private Long totalCost;

    private final List<FoodItemDraft> items = new ArrayList<>();

    public MealDraft() {
        this(null);
    }

    public MealDraft(String existingMealId) {
        this.existingMealId = existingMealId;
        this.eatenAt = LocalDateTime.now();
    }

    public String getExistingMealId() {
        return existingMealId;
    }

    public LocalDateTime getEatenAt() {
        return eatenAt;
    }

    public void setEatenAt(LocalDateTime eatenAt) {
        this.eatenAt = eatenAt;
    }

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public List<FoodItemDraft> getItems() {
        return items;
    }
}
