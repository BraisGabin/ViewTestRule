package com.android21buttons.viewtestrule;

import android.app.Activity;
import androidx.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;

public class ViewTestRule<A extends Activity, V extends View> extends ActivityTestRule<A> {
    private static final String TAG = "ViewTestRule";

    private final ViewFactory<A, V> viewFactory;
    private final boolean launchView;
    private V view;

    public static <V extends View> ViewTestRule<?, V> create(ViewFactory<Activity, V> viewFactory) {
        return new ViewTestRule<>(Activity.class, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, ViewFactory<Activity, V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, boolean launchActivity, ViewFactory<Activity, V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, launchActivity, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, boolean launchActivity, boolean launchView, ViewFactory<Activity, V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, launchActivity, launchView, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, ViewFactory<A, V> viewFactory) {
        this(activityClass, false, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, ViewFactory<A, V> viewFactory) {
        this(activityClass, initialTouchMode, true, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, boolean launchActivity, ViewFactory<A, V> viewFactory) {
        this(activityClass, initialTouchMode, launchActivity, true, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, boolean launchActivity, boolean launchView, ViewFactory<A, V> viewFactory) {
        super(activityClass, initialTouchMode, launchActivity);
        this.viewFactory = viewFactory;
        this.launchView = launchView;
    }

    @Override
    protected void afterActivityLaunched() {
        if (launchView) {
            launchView(null);
        }
    }

    public void launchView(final V view) {
        try {
            runOnUiThread(() -> {
                final V view2 = view == null ? createView() : view;
                ViewTestRule.this.view = view2;
                getActivity().setContentView(view2);
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    protected V createView() {
        final A activity = getActivity();
        return viewFactory.create(activity, activity.findViewById(android.R.id.content));
    }

    public V getView() {
        if (view == null) {
            Log.w(TAG, "View wasn't created yet");
        }
        return view;
    }
}
