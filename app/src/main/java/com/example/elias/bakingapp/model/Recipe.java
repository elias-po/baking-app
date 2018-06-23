package com.example.elias.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("ingredients")
    List<Ingredient> ingredients;
    @SerializedName("steps")
    List<Step> steps;
    @SerializedName("servings")
    int servingCount;
    @SerializedName("image")
    String image;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public void setServingCount(int servingCount) {
        this.servingCount = servingCount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServingCount() {
        return servingCount;
    }

    public String getImage() {
        return image;
    }
}
