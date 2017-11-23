package com.android21buttons.viewtestrule;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface ViewFactory<T extends View> {

    T create(Activity activity, ViewGroup parent);
}
