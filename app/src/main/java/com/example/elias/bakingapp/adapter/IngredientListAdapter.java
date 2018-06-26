package com.example.elias.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class IngredientListAdapter extends RecyclerView.Adapter {
    private final ArrayList<Ingredient> ingredients;

    public IngredientListAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((IngredientViewHolder) holder).bindData(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.ingredients_list_rv_item;
    }
}
