package com.android21buttons.viewtestrule;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class ViewTestRule<A extends Activity, V extends View> extends ActivityTestRule<A> {
    private static final String TAG = "FragmentTestRule";

    private final ViewFactory<V> viewFactory;
    private final boolean launchView;
    private V view;

    public static <V extends View> ViewTestRule<?, V> create(ViewFactory<V> viewFactory) {
        return new ViewTestRule<>(Activity.class, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, ViewFactory<V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, boolean launchActivity, ViewFactory<V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, launchActivity, viewFactory);
    }

    public static <V extends View> ViewTestRule<?, V> create(boolean initialTouchMode, boolean launchActivity, boolean launchView, ViewFactory<V> viewFactory) {
        return new ViewTestRule<>(Activity.class, initialTouchMode, launchActivity, launchView, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, ViewFactory<V> viewFactory) {
        this(activityClass, false, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, ViewFactory<V> viewFactory) {
        this(activityClass, initialTouchMode, true, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, boolean launchActivity, ViewFactory<V> viewFactory) {
        this(activityClass, initialTouchMode, launchActivity, true, viewFactory);
    }

    public ViewTestRule(Class<A> activityClass, boolean initialTouchMode, boolean launchActivity, boolean launchView, ViewFactory<V> viewFactory) {
        super(activityClass, initialTouchMode, launchActivity);
        this.viewFactory = viewFactory;
        this.launchView = launchView;
    }

    @Override
    protected void afterActivityLaunched() {
        if (launchView) {
            launchView(createView());
        }
    }

    public void launchView(final V view) {
        try {
            final V view2 = view == null ? createView() : view;
            ViewTestRule.this.view = view2;
            runOnUiThread(() -> getActivity().setContentView(view2));
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
