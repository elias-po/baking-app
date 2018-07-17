package com.example.elias.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.elias.bakingapp.model.Step;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeDetailViewActivity}.
 */
public class StepDetailActivity extends AppCompatActivity {
    String TAG = "STEP_DETAIL_ACTIVITY";

    BottomNavigationView navigation;
    @Nullable
    @BindView(R.id.iv_toolbar_thumbnail)
    ImageView thumbnailIv;

    private Step step;
    private boolean landscapeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        Bundle passed_extras = getIntent().getExtras();
        step = passed_extras.getParcelable(StepDetailFragment.STEP_KEY);
        landscapeLayout = thumbnailIv == null;
        if (!landscapeLayout) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            fab.setVisibility(View.GONE);

            // Show the Up button in the action bar.
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            String thumbnail_url = step.getThumbnailURL();
            if (!thumbnail_url.isEmpty()) {
                Picasso.with(this)
                        .load(thumbnail_url)
                        .fit()
                        .into(thumbnailIv);
            } else {
                thumbnailIv.setVisibility(View.GONE);
            }

            navigation = findViewById(R.id.navigation);
            if (navigation != null) {
                //TODO: implement navigation between steps (including Ingredients) later
                navigation.setVisibility(View.GONE);
                navigation.getMenu().getItem(0).setCheckable(false);
                navigation.getMenu().getItem(1).setCheckable(false);
                navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return false;
                    }
                });
            }
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the step detail fragment and add it to the activity
            // using a fragment transaction.
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(passed_extras);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, RecipeDetailViewActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
