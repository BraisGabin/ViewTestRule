package com.android21buttons.viewtestrule.sample;

import android.widget.TextView;

import com.android21buttons.viewtestrule.ViewTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DelayedLaunchTest {

    @Rule
    public ViewTestRule<DebugActivity, TextView> viewTestRule =
            new ViewTestRule<>(DebugActivity.class,
                    false,
                    true,
                    false,
                    (context, parent) -> {
                        final TextView textView = new TextView(context);
                        textView.setId(R.id.v_for_view);
                        return textView;
                    });


    @Test
    public void checkNotLaunched() throws Throwable {
        onView(withId(R.id.v_for_view)).check(doesNotExist());
    }

    @Test
    public void launchView_withNull() throws Throwable {
        viewTestRule.launchView(null);

        onView(withId(R.id.v_for_view)).check(matches(withText("")));
    }

    @Test
    public void launchView_withView() throws Throwable {
        final TextView textView = new TextView(viewTestRule.getActivity());
        textView.setId(R.id.v_for_view);
        textView.setText("Testing");
        viewTestRule.launchView(textView);

        onView(withId(R.id.v_for_view)).check(matches(withText("Testing")));
    }
}
