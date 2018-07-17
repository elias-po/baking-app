package com.example.elias.bakingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link RecipeDetailViewActivity}
 * in two-pane mode (on tablets) or a {@link StepDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends Fragment {
    String TAG = "STEP_DETAIL_FRAGMENT";

    public static final String STEP_KEY = "step_key";
    private Step step;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    private boolean landscapeLayout;

    SimpleExoPlayer player;
    @BindView(R.id.playerView)
    PlayerView playerView;

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
        ButterKnife.bind(this, rootView);
        playerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.exo_controls_play));

        if (step != null) {
            TextView video_placeholder = rootView.findViewById(R.id.video_placeholder);
            ImageView stepThumbnailIv = rootView.findViewById(R.id.iv_fragment_thumbnail);
            TextView stepDescriptionTv = rootView.findViewById(R.id.tv_step_description);
            String video_url = step.getVideoURL();
            String thumbnail_url = step.getThumbnailURL();

            if (stepDescriptionTv != null) {
            // Vertical/tablet layout
                landscapeLayout = false;
                // Setting up video and thumbnail views
                if (!video_url.isEmpty()) {
                    // if a video is present
                    video_placeholder.setText(video_url);
                    if (stepThumbnailIv != null) {
                        stepThumbnailIv.setVisibility(View.GONE);
                        Log.d(TAG, "Unnecessary image view hidden");
                    }
                } else {
                    // if no video was provided
                    // hide the view
                    video_placeholder.setVisibility(View.GONE);
                    playerView.setVisibility(View.GONE);
                    Log.d(TAG, "Video view hidden - no url provided");
                    if (!thumbnail_url.isEmpty()) {
                        // if thumbnail was provided instead
                        if (stepThumbnailIv != null) {
                            Picasso.with(rootView.getContext())
                                    .load(thumbnail_url)
                                    .fit()
                                    .into(stepThumbnailIv);
                            Log.d(TAG, "Binded thumbnail from" + thumbnail_url);
                        }
                    } else {
                        // if no thumbnail was NOT provided (and no video)
                        // (alternatively, can be left with 'no image' sign)
                        if (stepThumbnailIv != null) {
                            stepThumbnailIv.setVisibility(View.GONE);
                        }
                        Log.d(TAG, "Thumbnail view hidden - no url provided");
                    }
                }
            } else {
            // Landscape layout
                landscapeLayout = true;
                if (!video_url.isEmpty()) {
                    // if a video is present
                    stepThumbnailIv.setVisibility(View.GONE);
                    Log.d(TAG, "Unnecessary image view hidden");

                } else {
                    // if no video was provided
                    // hide the view
                    playerView.setVisibility(View.GONE);
                    Log.d(TAG, "Video view hidden - no url provided");
                    if (!thumbnail_url.isEmpty()) {
                        // if thumbnail was provided instead
                        Picasso.with(rootView.getContext())
                                .load(thumbnail_url)
                                .fit()
                                .into(stepThumbnailIv);
                        Log.d(TAG, "Binded thumbnail from" + thumbnail_url);
                    } else {
                        // if no thumbnail was NOT provided (and no video)
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

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (landscapeLayout) {
            hideSystemUi();
        }
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    // Most of the ExoPlayer-related code was written following this codelab: https://codelabs.developers.google.com/codelabs/exoplayer-intro
    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }
        MediaSource mediaSource = buildMediaSource(Uri.parse(step.getVideoURL()));
        player.prepare(mediaSource, true, false);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab"))
                .createMediaSource(uri);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
