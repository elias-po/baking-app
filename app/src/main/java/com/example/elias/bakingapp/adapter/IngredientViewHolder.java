package com.example.elias.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.model.Ingredient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ingredient_name_tv)
    TextView ingredient_name_tv;

    @BindView(R.id.ingredient_quantity_tv)
    TextView ingredient_quantity_tv;

    @BindView(R.id.ingredient_measure_tv)
    TextView ingredient_measure_tv;


    public IngredientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(Ingredient ingredient) {
        ingredient_name_tv.setText(ingredient.getIngredient());
        Double qty = ingredient.getQuantity();
        ingredient_quantity_tv.setText(String.valueOf(qty));
        String measure = ingredient.getMeasure();
        if (qty >= 2) {
            measure += "s";
        }
        ingredient_measure_tv.setText(measure);
    }
}
