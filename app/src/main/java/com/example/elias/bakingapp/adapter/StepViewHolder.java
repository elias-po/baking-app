package com.example.elias.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.elias.bakingapp.R;
import com.example.elias.bakingapp.RecipeDetailViewActivity;
import com.example.elias.bakingapp.StepDetailActivity;
import com.example.elias.bakingapp.StepDetailFragment;
import com.example.elias.bakingapp.dummy.DummyContent;
import com.example.elias.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        DummyContent.DummyItem item = (DummyContent.DummyItem) v.getTag();
        if (mTwoPane) {
            Bundle extras = new Bundle();
            extras.putString(StepDetailFragment.ARG_ITEM_ID, item.id);
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(extras);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, StepDetailActivity.class);
            intent.putExtra(StepDetailFragment.ARG_ITEM_ID, item.id);

            context.startActivity(intent);
        }
    }
}
