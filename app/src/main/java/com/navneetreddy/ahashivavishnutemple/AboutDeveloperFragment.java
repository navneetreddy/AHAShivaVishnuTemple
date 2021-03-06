package com.navneetreddy.ahashivavishnutemple;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * Fragment containing information about the developer of this app - Navneet Reddy.
 *
 * @author Navneet Reddy
 */
public class AboutDeveloperFragment extends Fragment {

    private Transformation transformation;

    private Animator currentAnimator;
    private int animationDuration;

    private int navneetImageID;

    private View rootView;
    private LinearLayout backgroundView;

    private View emailButton;

    private ImageButton linkedInButton;
    private ImageButton githubButton;

    private ImageButton imageThumb;
    private ImageView expandedImage;

    private TextView description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_about_developer, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFields(view);
        setDescription();

        buttonClickListeners();
        imageThumbClickListener();
    }

    @Override
    public void onResume() {
        super.onCreate(null);

        linkedInButton.setEnabled(true);
        githubButton.setEnabled(true);
        emailButton.setEnabled(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onCreate(null);
    }

    /**
     * Initializes all the child views and loads the images into the image views and image buttons.
     *
     * @param view Root view of the fragment.
     */
    private void initializeFields(View view) {
        backgroundView = (LinearLayout) view.findViewById(R.id.navneetFragmentLinearLayout);

        emailButton = view.findViewById(R.id.navneetEmailButton);

        linkedInButton = (ImageButton) view.findViewById(R.id.linkedinImageButton);
        githubButton = (ImageButton) view.findViewById(R.id.githubImageButton);
        imageThumb = (ImageButton) view.findViewById(R.id.navneetPicture);
        expandedImage = (ImageView) view.findViewById(R.id.navneetExpandedImage);

        description = (TextView) view.findViewById(R.id.navneetDescription);

        navneetImageID = R.drawable.navneet_picture_1;

        transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(50)
                .oval(false)
                .build();

        Picasso.with(getActivity())
                .load(navneetImageID)
                .transform(transformation)
                .into(imageThumb);

        Picasso.with(getActivity())
                .load(R.drawable.linkedin_logo)
                .into(linkedInButton);

        Picasso.with(getActivity())
                .load(R.drawable.github_logo)
                .into(githubButton);

        /* Load the email icon only if the orientation of the device is in landscape. */
        getActivity().getResources().getConfiguration();
        if (getActivity().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getActivity())
                    .load(R.drawable.email_icon)
                    .into((ImageButton) emailButton);
        }
    }

    /**
     * Sets the description for the about Navneet section.
     */
    private void setDescription() {
        String descriptionText = "Navneet is currently a senior at the University of Wisconsin " +
                "- Madison, completing his Bachelor's degree in December 2015.\n\n" +
                "Outside of computing and software development, Navneet enjoys refereeing soccer" +
                ", all kinds of sports and exploring the world.\n\n" +
                "Navneet's favorite sports teams include: Wisconsin Badgers, Green Bay Packers, " +
                "Milwaukee Brewers, Milwaukee Bucks, and Stewart-Haas Racing.\n\n" +
                "The code for this app is hosted on Navneet's GitHub profile under the " +
                "repository name \"AHAShivaVishnuTemple\"";

        description.setText(descriptionText);
    }

    /**
     * Handles the clicks on the LinkedIn, GitHub, and email buttons.
     */
    private void buttonClickListeners() {
        linkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedInButton.setEnabled(false);

                goToWebsite("http://www.linkedin.com/in/navneetreddy");
            }
        });

        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubButton.setEnabled(false);

                goToWebsite("http://www.github.com/navneetreddy");
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailButton.setEnabled(false);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"navneet@tds.net"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message from AHA Android App");

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException ae1) {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                    playStoreIntent.setData(
                            Uri.parse("market://details?id=com.google.android.gm"));

                    try {
                        startActivity(playStoreIntent);
                    } catch (ActivityNotFoundException ae2) {
                        new AlertDialog.Builder(Singleton.getContext())
                                .setCancelable(true)
                                .setIcon(android.R.drawable.stat_sys_warning)
                                .setTitle("No Email Client Found!")
                                .setMessage("Please download an email client to send email.")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                        emailButton.setEnabled(true);
                    }
                }
            }
        });
    }

    /**
     * Starts an intent to direct the user to the given url.
     *
     * @param url URL of the desired website to direct the user to.
     */
    private void goToWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /**
     * On click listener for the image thumbnail.
     */
    private void imageThumbClickListener() {
        imageThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb();
            }
        });
    }

    /**
     * Expands the image thumbnail to full screen using a custom animation.
     */
    private void zoomImageFromThumb() {
        animationDuration = Singleton.getContext()
                .getResources().getInteger(android.R.integer.config_shortAnimTime);

        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (currentAnimator != null) {
            currentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        Picasso.with(getActivity())
                .load(navneetImageID)
                .transform(transformation)
                .into(expandedImage);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        imageThumb.getGlobalVisibleRect(startBounds);
        rootView.getGlobalVisibleRect(finalBounds, globalOffset);

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;

        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {

            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();

            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;

            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();

            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;

            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        prepareVisibilitiesForAnimation();

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the
        // top-left corner of the zoomed-in view (the default is the center of the view).
        expandedImage.setPivotX(0f);
        expandedImage.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(expandedImage, View.X,
                startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImage, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImage, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImage,
                View.SCALE_Y, startScale, 1f));

        set.setDuration(animationDuration);
        set.setInterpolator(new DecelerateInterpolator());

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                currentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                currentAnimator = null;
            }
        });

        set.start();
        currentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;

        expandedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentAnimator != null) {
                    currentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImage, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImage,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImage,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImage,
                                        View.SCALE_Y, startScaleFinal));

                set.setDuration(animationDuration);
                set.setInterpolator(new DecelerateInterpolator());

                // Sets the view to the normal view when animation is either done or cancelled.
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        endAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        endAnimation();
                    }
                });

                set.start();
                currentAnimator = set;
            }
        });
    }

    /**
     * Dims the background and hides all the views in preparation of expanding the image.
     */
    private void prepareVisibilitiesForAnimation() {
        imageThumb.setAlpha(0f);
        backgroundView.setAlpha(.7f);
        backgroundView.setBackgroundColor(Color.BLACK);

        int childViews = backgroundView.getChildCount();

        for (int i = 0; i < childViews; i++) {
            backgroundView.getChildAt(i).setVisibility(View.GONE);
        }

        expandedImage.setVisibility(View.VISIBLE);
        expandedImage.setAlpha(1f);
    }

    /**
     * Restores the initial state of all the views.
     */
    protected void endAnimation() {
        imageThumb.setAlpha(1f);
        backgroundView.setAlpha(1f);
        backgroundView.setBackgroundColor(Color.TRANSPARENT);

        int childViews = backgroundView.getChildCount();

        for (int i = 0; i < childViews; i++) {
            backgroundView.getChildAt(i).setVisibility(View.VISIBLE);
        }

        expandedImage.setVisibility(View.GONE);
        currentAnimator = null;
    }
}