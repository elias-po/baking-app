package com.example.elias.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.elias.bakingapp.Utils.setRecipesAdapter;

public class RecipeListActivity extends AppCompatActivity {
    @BindView(R.id.rv_recipe_list)
    RecyclerView rv_recipeList;
    private LinearLayoutManager recipeListLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        LinearLayout recipe_list_wrapper = findViewById(R.id.recipe_list_wrapper);
        if (recipe_list_wrapper == null) {
            recipeListLayoutManager = new LinearLayoutManager(this);
        } else {
            recipeListLayoutManager = new GridLayoutManager(this, 3);
        }
        rv_recipeList.setLayoutManager(recipeListLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecipesAdapter(rv_recipeList);
    }
}
