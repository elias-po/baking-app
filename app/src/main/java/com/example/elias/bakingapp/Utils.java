package com.example.elias.bakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.elias.bakingapp.adapter.RecipeListRecyclerViewAdapter;
import com.example.elias.bakingapp.dummy.DummyContent;
import com.example.elias.bakingapp.model.Recipe;
import com.example.elias.bakingapp.rest.ApiClient;
import com.example.elias.bakingapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class Utils {

    public static void setRecipesAdapter(final RecyclerView rv_reference){
        final String TAG = "RECIPE_REQUEST";

        ApiInterface apiService = ApiClient
                .getClient(rv_reference.getContext(), rv_reference)
                .create(ApiInterface.class);

        Call<List<Recipe>> call = apiService.getRecipeList();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipes = response.body();
                Log.d(TAG, "Number of recipes received: " + recipes.size());
                rv_reference.setAdapter(new RecipeListRecyclerViewAdapter(recipes));
                Log.d(TAG, "Recipes adapter attached");
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }

}
