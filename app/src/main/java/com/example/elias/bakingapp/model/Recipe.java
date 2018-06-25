package com.example.elias.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe  implements Parcelable{
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


    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        // auto-generated stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeInt(servingCount);
        dest.writeString(image);
    }

    private Recipe(Parcel source) {
        id = source.readInt();
        name = source.readString();
        source.readTypedList(ingredients, Ingredient.CREATOR);
        source.readTypedList(steps, Step.CREATOR);
        servingCount = source.readInt();
        image = source.readString();
    }

}
