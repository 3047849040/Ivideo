package com.example.ivideo.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyLayourtManager extends LinearLayoutManager {
    private PagerSnapHelper helper;
    public MyLayourtManager(Context context) {
        super(context);
    }

    public MyLayourtManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLayourtManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(view);
    }
}
