package it.unisa.guardianhouse.slider;

/**
 * Created by Luigi on 14/05/2015.
 *
 * Classe per Creare uno slider di oggetti all'interno della Home
 * Usa Lo SliderAdapter.java presente nella stessa cartella
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Slider extends ViewPager implements OnItemClickListener {

    OnSliderListener mCallback;

    private Timer timer;
    private int currentIndex = 0;
    private Boolean isTimerRunning;

    private Activity activity;
    public ImageView[] imageViews;
    public Boolean isPaused = false;
    public int maxSliderCount = 0;

    public Slider(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public interface OnSliderListener {
        public void onItemMGSliderToView(Slider slider, int pos);
        public void onItemMGSliderViewClick(AdapterView<?> adapterView, View v, int pos, long resid);
        public void onItemThumbCreated(Slider slider, ImageView imgView, int pos);
        public void onAllItemThumbCreated(Slider slider, LinearLayout linearLayout);
        public void onItemThumbSelected(Slider slider, ImageView[] buttonPoint, ImageView imgView, int pos);
        public void onItemPageScrolled(Slider slider, ImageView[] buttonPoint, int pos);
    }


    public void setMaxSliderThumb(int count) {
        maxSliderCount = count;
    }

    public void setOnMGSliderListener(OnSliderListener listener) {
        try {
            mCallback = (OnSliderListener) listener;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + " must implement OnSliderListener");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int pos, long resid) {
        // TODO Auto-generated method stub
        mCallback.onItemMGSliderViewClick(adapterView, v, pos, resid);
    }




    @Override
    protected void onPageScrolled(int position, float positionOffset,
                                  int positionOffsetPixels) {
        // TODO Auto-generated method stub
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

        if(mCallback != null)
            mCallback.onItemPageScrolled(this, imageViews, position);

        this.currentIndex = position;
    }



    public void setSliderAnimation(long delay) {
        isTimerRunning = false;

        if(timer == null)
            timer = new Timer();

        if(isTimerRunning) {
            Log.e("Timer is currently running",
                    "Timer is currently running.please stop the slider animation first by " +
                            "calling Slider.stopSliderAnimation()");

            return;
        }

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                activity.runOnUiThread(new Runnable() {

                    public void run() {
                        if(!isPaused)
                            setCurrentSlide();
                    }
                });
            }
        };

        setSlideAtIndex(0);
        timer.schedule(timerTask, delay, delay);
        this.isTimerRunning = true;
    }

    public void stopSliderAnimation() {

        if(timer != null) {
            timer.cancel();
            timer.purge();
            this.isTimerRunning = false;
            timer = null;
        }
    }

    public void pauseSliderAnimation() {
        isPaused = true;
    }

    public void resumeSliderAnimation() {
        isPaused = false;
    }

    public void showThumb() {

        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        SliderAdapter adapter = (SliderAdapter)this.getAdapter();
        imageViews = new ImageView[adapter.maxThumbCount];

        for(int x = 0; x < adapter.maxThumbCount; x++) {
            final ImageView img = new ImageView(activity);
            img.setAdjustViewBounds(true);
            android.widget.LinearLayout.LayoutParams lp = new
                    LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;

            if(x < adapter.maxThumbCount - 1) {
                lp.setMargins(0, 0, 5, 0);
            }
            img.setLayoutParams(lp);
            img.setTag(x);



            img.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    // TODO Auto-generated method stub
                    int pos = Integer.parseInt(img.getTag().toString());
                    Slider.this.setCurrentItem(pos, true);

                    mCallback.onItemThumbSelected(Slider.this, imageViews, img, pos);
                    currentIndex = pos;
                }
            });

            mCallback.onItemThumbCreated(this, img, x);

            linearLayout.addView(img);
            imageViews[x] = img;
        }

        mCallback.onAllItemThumbCreated(this, linearLayout);
    }


    public void setCurrentSlide() {
        SliderAdapter sliderAdapter = (SliderAdapter)this.getAdapter();
        // if page is less than the slider array - 1, display it
        if( this.currentIndex < sliderAdapter.maxThumbCount - 1) {
            this.currentIndex++;
            this.setCurrentItem(currentIndex, true);
        }
        // else start sliding from 1
        else {
            this.setCurrentItem(0, true);
            this.currentIndex = 0;
        }

        if(mCallback != null)
            mCallback.onItemMGSliderToView(this, currentIndex);
    }

    public void setSlideAtIndex(int index) {
        this.setCurrentItem(index, true);
        this.currentIndex = index;

        if(mCallback != null)
            mCallback.onItemMGSliderToView(this, index);
    }

}
