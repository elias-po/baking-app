package com.example.elias.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.bakingapp.model.Recipe;

import java.util.List;

public class RecipeListRecyclerViewAdapter extends RecyclerView.Adapter{

    private List<Recipe> recipes;
    private Context context;

    public RecipeListRecyclerViewAdapter(List<Recipe> rcps) {
        recipes = rcps;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        this.context = parent.getContext();
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}