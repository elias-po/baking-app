package com.example.elias.bakingapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.bakingapp.adapter.IngredientListAdapter;
import com.example.elias.bakingapp.model.Ingredient;
import com.example.elias.bakingapp.model.IngredientParcelableList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a Ingredients list screen.
 * This fragment is either contained in a {@link RecipeDetailViewActivity}
 * in two-pane mode (on tablets) or a {@link IngredientsActivity}
 * on handsets.
 */
public class IngredientsFragment extends Fragment {
    String TAG = "INGREDIENTS_FRAGMENT";

    public static final String INGREDIENTS_KEY = "ingredients_key";
    private ArrayList<Ingredient> ingredients;

    @BindView(R.id.ingredient_list_rv)
    RecyclerView ingredients_rv;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ButterKnife.bind(this, getActivity());
        if (getArguments().containsKey(INGREDIENTS_KEY)) {
            ingredients = ((IngredientParcelableList) getArguments().getParcelable(INGREDIENTS_KEY)).getIngredients();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredients_list_rv, container, false);

        if (ingredients != null) {

            Log.i(TAG, "Got " + ingredients.size() + " ingredients");
            // Layout Manager is declared in the xml file
            ((RecyclerView) rootView.findViewById(R.id.ingredient_list_rv)).setAdapter(new IngredientListAdapter(ingredients));
            Log.i(TAG, "Adapter set onCreateView");
        }

        return rootView;
    }
}
