package com.example.elias.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        recipeListLayoutManager = new LinearLayoutManager(this);
        rv_recipeList.setLayoutManager(recipeListLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecipesAdapter(rv_recipeList);
    }
}
