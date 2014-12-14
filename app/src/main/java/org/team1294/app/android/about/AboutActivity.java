package org.team1294.app.android.about;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.team1294.app.android.R;


public class AboutActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
