package com.example.elias.bakingapp.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("quantity")
    int quantity;
    @SerializedName("measure")
    String measure;
    @SerializedName("ingredient")
    String ingredient;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
