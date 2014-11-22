package me.timtim17.dev.java.android.topgunrobotics.events.timer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import me.timtim17.dev.java.android.topgunrobotics.R;
import me.timtim17.dev.java.android.topgunrobotics.events.timer.util.SystemUiHider;
import me.timtim17.dev.java.android.topgunrobotics.fonts.Fonts;

public class TimerActivity extends ActionBarActivity {
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        TextView textView = (TextView) findViewById(R.id.timer);
        Typeface robotoThin = new Fonts(this).robotoThin;
        textView.setTypeface(robotoThin);

        final View contentView = findViewById(R.id.fullscreen_content);
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, SystemUiHider.FLAG_HIDE_NAVIGATION);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    @Override
                    public void onVisibilityChange(boolean visible) {
                        if (visible) {
                            delayedHide(3000);
                        }
                    }
                });
        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSystemUiHider.toggle();
            }
        });
        delayedHide(3000);
    }


    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
