package it.unisa.guardianhouse.refreshlayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

import it.unisa.guardianhouse.R;

public abstract class SwipeRefreshActivity extends FragmentActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.fragment_swipe_refresh);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
    }

    @Override public void setContentView(int layoutResID) {
        View v = getLayoutInflater().inflate(layoutResID, refreshLayout, false);
        setContentView(v);
    }

    @Override public void setContentView(View view) {
        setContentView(view, view.getLayoutParams());
    }

    @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
        refreshLayout.addView(view, params);
        initSwipeOptions();
    }

    private void initSwipeOptions() {
        refreshLayout.setOnRefreshListener(this);
        setAppearance();
        disableSwipe();
    }

    @SuppressWarnings("deprecation")
    private void setAppearance() {
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void showSwipeProgress() {
        refreshLayout.setRefreshing(true);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void hideSwipeProgress() {
        refreshLayout.setRefreshing(false);
    }

    /**
     * Enables swipe gesture
     */
    public void enableSwipe() {
        refreshLayout.setEnabled(true);
    }

    /**
     * Disables swipe gesture. It prevents manual gestures but keeps the option tu show
     * refreshing programatically.
     */
    public void disableSwipe() {
        refreshLayout.setEnabled(false);
    }

    /**
     * It must be overriden by parent classes if manual swipe is enabled.
     */
    @Override public void onRefresh() {
        // Empty implementation
    }
}
