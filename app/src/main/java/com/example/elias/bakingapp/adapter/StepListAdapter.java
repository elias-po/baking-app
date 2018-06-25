package com.example.elias.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.RecipeDetailViewActivity;
import com.example.elias.bakingapp.StepDetailActivity;
import com.example.elias.bakingapp.StepDetailFragment;
import com.example.elias.bakingapp.dummy.DummyContent;
import com.example.elias.bakingapp.model.Ingredient;
import com.example.elias.bakingapp.model.Recipe;
import com.example.elias.bakingapp.model.Step;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter {
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Step> steps;
    private final RecipeDetailViewActivity mParentActivity;


    private final boolean mTwoPane;
    public StepListAdapter(Recipe recipe, RecipeDetailViewActivity parent, boolean twoPane) {
        ingredients = recipe.getIngredients();
        steps = recipe.getSteps();
        mTwoPane = twoPane;
        mParentActivity = parent;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new StepViewHolder(view, mParentActivity, mTwoPane);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((StepViewHolder) holder).bindData(ingredients, position);
        } else {
            ((StepViewHolder) holder).bindData(steps.get(position-1), position);
        }
    }

    @Override
    public int getItemCount() {
        // for convenience, ingredients count as a step
        return steps.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.step_card;
    }

}
