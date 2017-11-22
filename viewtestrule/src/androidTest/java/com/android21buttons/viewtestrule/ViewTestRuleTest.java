package com.android21buttons.viewtestrule;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ViewTestRuleTest {

    @Test
    public void getViewNoCreate() throws Exception {
        ViewTestRule<?, View> testRule = new ViewTestRule<>(Activity.class, (context, parent) -> new View(context));

        assertThat(testRule.getView(), is(nullValue()));
    }
}
