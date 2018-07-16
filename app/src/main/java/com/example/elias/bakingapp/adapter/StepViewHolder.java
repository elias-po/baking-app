package com.example.elias.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.elias.bakingapp.IngredientsActivity;
import com.example.elias.bakingapp.IngredientsFragment;
import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.RecipeDetailViewActivity;
import com.example.elias.bakingapp.StepDetailActivity;
import com.example.elias.bakingapp.StepDetailFragment;
import com.example.elias.bakingapp.model.Ingredient;
import com.example.elias.bakingapp.model.IngredientParcelableList;
import com.example.elias.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    String TAG = "STEP_VIEW_HOLDER";
    // fields that don't change onBindViewHolder
    @BindView(R.id.step_short_description)
    TextView tv_step_short_description;
    private final boolean mTwoPane;
    private final RecipeDetailViewActivity mParentActivity;

    // fields that change onBindViewHolder
    private Object item; //either a step or a list of ingredients;
    private boolean isStep; // true - item is a Step, false - item is an ArrayList<Ingredient>
    private int position;



    public StepViewHolder(View itemView, RecipeDetailViewActivity p_activity, boolean twoPane) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mParentActivity = p_activity;
        this.mTwoPane = twoPane;
        itemView.setOnClickListener(this);
    }

    public void bindData(Object item, int pos) {
        this.item = item;
        this.isStep = pos != 0; // the 0th card is for Ingredients, every other for Steps
        this.position = pos;
        if (isStep){
            String step_card_text = pos + " " +((Step) item).getShortDescription();
            tv_step_short_description.setText(step_card_text);
        } else {
            tv_step_short_description.setText(R.string.ingredient_card_text);
        }
    }

    @Override
    public void onClick(View v) {
        switch (getAdapterPosition()){
            case 0:
                Log.i(TAG, "You've clicked ingredients card at position 0");
                break;
            default:
                Log.i(TAG, "You've clicked ingredients card at position " + getAdapterPosition());
        }

        if (mTwoPane) {
            // tablet layout
            Bundle extras = new Bundle();
            if(isStep) {
                //  Step card was chosen
                extras.putParcelable(StepDetailFragment.STEP_KEY, (Step) item);
                StepDetailFragment fragment = new StepDetailFragment();
                fragment.setArguments(extras);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                // Ingredients card was chosen
                extras.putParcelable(IngredientsFragment.INGREDIENTS_KEY, new IngredientParcelableList((ArrayList<Ingredient>) item));
                IngredientsFragment fragment = new IngredientsFragment();
                fragment.setArguments(extras);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            }
        } else {
            // phone layout
            Bundle extras = new Bundle();
            Context context = v.getContext();
            if (isStep){
                // Step card was chosen
                Intent intent = new Intent(context, StepDetailActivity.class);
                // TODO: pass the whole list of steps in order to implement navigation?
                extras.putParcelable(StepDetailFragment.STEP_KEY, (Step) item);
                intent.putExtras(extras);
                context.startActivity(intent);
            } else {
                // Ingredients card was chosen
                Intent intent = new Intent(context, IngredientsActivity.class);
                extras.putParcelable(IngredientsFragment.INGREDIENTS_KEY, new IngredientParcelableList((ArrayList<Ingredient>) item));
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        }
    }
}
