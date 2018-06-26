package com.example.elias.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elias.bakingapp.model.Ingredient;
import com.example.elias.bakingapp.model.IngredientParcelableList;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    String TAG = "INGREDIENTS_ACTIVITY";
    IngredientParcelableList parcelable_ingredients_list;
    ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        if (savedInstanceState == null) {
            // Create the ingredient list fragment and add it to the activity
            // using a fragment transaction.
            Bundle passed_extras = getIntent().getExtras();
            IngredientsFragment fragment = new IngredientsFragment();
            fragment.setArguments(passed_extras);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ingredient_list_container, fragment)
                    .commit();
        }
    }
}
