package com.android21buttons.viewtestrule.sample;

import android.widget.TextView;

import com.android21buttons.viewtestrule.ViewTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class DefaultActivityTest {

    @Rule
    public ViewTestRule<?, TextView> viewTestRule =
            ViewTestRule.create((context, parent) -> {
                final TextView textView = new TextView(context);
                textView.setId(R.id.v_for_view);
                return textView;
            });

    @Test
    public void checkEmpty() throws Throwable {
        onView(withId(R.id.v_for_view)).check(matches(withText("")));
    }

    @Test
    public void checkSetText() throws Throwable {
        viewTestRule.runOnUiThread(() -> viewTestRule.getView().setText("Testing"));

        onView(withId(R.id.v_for_view)).check(matches(withText("Testing")));
    }
}
