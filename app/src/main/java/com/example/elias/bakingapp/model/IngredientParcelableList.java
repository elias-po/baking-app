package com.example.elias.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class IngredientParcelableList implements Parcelable{

    ArrayList<Ingredient> ingredients = new ArrayList<>();

    public IngredientParcelableList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public IngredientParcelableList(Parcel in) {
        in.readTypedList(ingredients, Ingredient.CREATOR);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public static final Creator<IngredientParcelableList> CREATOR = new Creator<IngredientParcelableList>() {
        @Override
        public IngredientParcelableList createFromParcel(Parcel in) {
            return new IngredientParcelableList(in);
        }

        @Override
        public IngredientParcelableList[] newArray(int size) {
            return new IngredientParcelableList[size];
        }
    };

    @Override
    public int describeContents() {
        // auto-generated stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(ingredients);
    }
}
