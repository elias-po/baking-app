package com.example.elias.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    String TAG = "RECIPE_VIEW_HOLDER";

    @BindView(R.id.recipe_name)
    TextView tv_recipeName;

    @BindView(R.id.recipe_servings_count)
    TextView tv_recipeServingsCount;

    @BindView(R.id.recipe_image)
    ImageView iv_recipeImage;

    private Recipe recipe;
    private Context context;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bindData(Recipe recipe, Context context){
        this.recipe = recipe;
        this.context = context;

        String image_url = recipe.getImage();
        if (!image_url.isEmpty()){
            Picasso.with(itemView.getContext())
                    .load(image_url)
                    .fit()
                    .into(iv_recipeImage);
            Log.d(TAG, "Binded image from" + image_url);
        } else {
            Log.d(TAG, "No image to bind!");
        }

        String recipe_name = recipe.getName();
        String recipe_servings_count = String.valueOf(recipe.getServingCount());
        Log.d(TAG, "Binding name " + recipe_name);
        tv_recipeName.setText(recipe_name);
        Log.d(TAG, "Binding servings count " + recipe_servings_count);
        tv_recipeServingsCount.setText(recipe_servings_count);
    }

    @Override
    public void onClick(View v) {

    }
}
