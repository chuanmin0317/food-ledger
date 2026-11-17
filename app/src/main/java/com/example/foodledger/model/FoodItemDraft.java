package com.example.foodledger.model;

public class FoodItemDraft {
    private String name;
    private String unitDescription;

    // 實際吃了幾個單位
    private Double consumedUnits;

    // 單位營養
    private Double caloriesKcal;
    private Double proteinG;
    private Double fatG;
    private Double carbsG;

    private NutritionSource source; // LABEL, PHOTO_ESTIMATION, MANUAL

    public FoodItemDraft() {
        source = NutritionSource.MANUAL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public Double getConsumedUnits() {
        return consumedUnits;
    }

    public void setConsumedUnits(Double consumedUnits) {
        this.consumedUnits = consumedUnits;
    }

    public Double getCaloriesKcal() {
        return caloriesKcal;
    }

    public void setCaloriesKcal(Double caloriesKcal) {
        this.caloriesKcal = caloriesKcal;
    }

    public Double getProteinG() {
        return proteinG;
    }

    public void setProteinG(Double proteinG) {
        this.proteinG = proteinG;
    }

    public Double getFatG() {
        return fatG;
    }

    public void setFatG(Double fatG) {
        this.fatG = fatG;
    }

    public Double getCarbsG() {
        return carbsG;
    }

    public void setCarbsG(Double carbsG) {
        this.carbsG = carbsG;
    }

    public NutritionSource getSource() {
        return source;
    }

    public void setSource(NutritionSource source) {
        this.source = source;
    }

}
