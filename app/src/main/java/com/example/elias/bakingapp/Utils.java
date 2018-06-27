package com.example.elias.bakingapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.elias.bakingapp.adapter.RecipeListAdapter;
import com.example.elias.bakingapp.model.Recipe;
import com.example.elias.bakingapp.model.Step;
import com.example.elias.bakingapp.rest.ApiClient;
import com.example.elias.bakingapp.rest.ApiInterface;

import java.util.ArrayList;
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
                checkAndCorrectData(recipes);
                rv_reference.setAdapter(new RecipeListAdapter(recipes));
                Log.d(TAG, "Recipes adapter attached");
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    static private void checkAndCorrectData(List<Recipe> recipes) {
        final String TAG = "CHECK_AND_CORRECT_DATA";
        String video_url, thumbnail_url;
        for(Recipe recipe : recipes) {
            for(Step step : recipe.getSteps()) {
                video_url = step.getVideoURL();
                thumbnail_url = step.getThumbnailURL();
                if(video_url.isEmpty()) {
                    // if video_url is empty, but thumbnail_url contains a video file
                    if(!thumbnail_url.isEmpty() && thumbnail_url.substring(thumbnail_url.length()-4, thumbnail_url.length()).equals(".mp4")) {
                        step.setVideoURL(thumbnail_url);
                        step.setThumbnailURL("");
                    }
                } else {
                    // if thumbnail_url is empty, but video_url contains an image file
                    if(thumbnail_url.isEmpty() && ((video_url.substring(video_url.length()-4, video_url.length()).equals(".jpg"))
                                                    || (video_url.substring(video_url.length()-5, video_url.length()).equals(".jpeg"))
                                                    || (video_url.substring(video_url.length()-4, video_url.length()).equals(".png"))
                                                    || (video_url.substring(video_url.length()-4, video_url.length()).equals(".gif")))) {
                        step.setVideoURL("");
                        step.setThumbnailURL(video_url);
                    }
                }
            }
        }
    }

}
