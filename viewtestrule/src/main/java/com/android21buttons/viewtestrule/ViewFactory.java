package com.android21buttons.viewtestrule;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface ViewFactory<A extends Activity, T extends View> {

    T create(A activity, ViewGroup parent);
}
