package com.example.elias.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.elias.bakingapp.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.recipe_name)
    TextView tv_recipeName;

    @BindView(R.id.recipe_servings_count)
    TextView tv_recipeServingsCount;

    @BindView(R.id.recipe_image)
    ImageView iv_recipeImage;


    public RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void bindData(){
        //TODO: bind data here

    }

    @Override
    public void onClick(View v) {

    }
}
