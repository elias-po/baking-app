package com.example.elias.bakingapp;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elias.bakingapp.dummy.DummyContent;
import com.example.elias.bakingapp.model.Step;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link RecipeDetailViewActivity}
 * in two-pane mode (on tablets) or a {@link StepDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends Fragment {
    String TAG = "STEP_DETAIL_FRAGMENT";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String STEP_KEY = "step_key";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    private Step step;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(STEP_KEY)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            step = getArguments().getParcelable(STEP_KEY);
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                String newTitle = "Step " + (step.getId()+1);
                appBarLayout.setTitle(newTitle);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_detail, container, false);

        if (step != null) {
            TextView video_placeholder = rootView.findViewById(R.id.video_placeholder);
            ImageView stepThumbnailIv = rootView.findViewById(R.id.iv_step_thumbnail);
            TextView stepDescriptionTv = rootView.findViewById(R.id.tv_step_description);

            // Setting up video and thumbnail views
            String video_url = step.getVideoURL();
            String thumbnail_url = step.getThumbnailURL();
            if(!video_url.isEmpty()) {
                // if a video is present
                video_placeholder.setText(video_url);
                Log.d(TAG, "Video set from " + video_url);
                if(stepThumbnailIv != null) {
                    // if thumbnail image view is present, set to GONE - it is not needed
                    stepThumbnailIv.setVisibility(View.GONE);
                    Log.d(TAG, "Unnecessary image view hidden");
                } else {
                    Log.d(TAG, "No unnecessary image view to hide");
                }
            } else {
                // if no video was provided
                // hide the view
                video_placeholder.setVisibility(View.GONE);
                Log.d(TAG, "Video view hidden - no url provided");
                if (!thumbnail_url.isEmpty()) {
                    // if thumbnail was provided instead
                    if (stepThumbnailIv != null) {
                        // and it's view is present
                        Picasso.with(rootView.getContext())
                                .load(thumbnail_url)
                                .fit()
                                .into(stepThumbnailIv);
                        Log.d(TAG, "Binded thumbnail from" + thumbnail_url);
                    } else {
                        Log.d(TAG, "Thumbnail url provided, but not it's view");
                    }
                } else {
                    // if no thumbnail was NOT provided (and no video)
                    if (stepThumbnailIv != null) {
                        // but thumbnail's view is present, set to GONE
                        // alternatively, can be left with 'no image' sign
                        stepThumbnailIv.setVisibility(View.GONE);
                        Log.d(TAG, "Thumbnail view hidden - no url provided");
                    }
                }
            }

            if(stepDescriptionTv != null) {
                stepDescriptionTv.setText(step.getDescription());
            }
        }

        return rootView;
    }
}
