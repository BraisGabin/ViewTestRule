package com.android21buttons.viewtestrule;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface ViewFactory<T extends View> {

    T create(Context context, ViewGroup parent);
}
