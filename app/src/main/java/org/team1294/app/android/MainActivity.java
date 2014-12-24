package org.team1294.app.android;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequestInitializer;
import com.google.api.services.calendar.CalendarScopes;

import org.team1294.app.android.about.AboutActivity;
import org.team1294.app.android.events.Event;
import org.team1294.app.android.events.Events;
import org.team1294.app.android.events.cal.RetrieveNextMeeting;
import org.team1294.app.android.events.timer.EventCountdown;
import org.team1294.app.android.events.timer.TimerActivity;
import org.team1294.app.android.fonts.Fonts;
import org.team1294.app.android.sponsors.SponsorActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    // TODO: Create JavaDoc Comments

    Event currentEvent = Events.getNextEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(currentEvent != null) {
            LinearLayout timerView = (LinearLayout) findViewById(R.id.timer_layout);
            LinearLayout afterCountdownDoneShowView = (LinearLayout) findViewById(R.id.layout_livestream);
            new EventCountdown(currentEvent, timerView, R.id.timer, R.id.event, afterCountdownDoneShowView, true);
        }else{
            {
                CardView cv = (CardView) findViewById(R.id.next_meeting);
                cv.setVisibility(View.GONE);
            }
        }

            new RetrieveNextMeeting(this, R.id.next_meeting, R.id.holder, R.id.text_days_to_meeting, R.id.text_time_location, R.id.loadingSpinnerMeeting).execute();
    }

    /* Event Handlers */

    /*
     * This group of event handlers takes the user to a browser and a specific URL depending on the button they click in the main layout.
     */
    /* Opens User's Browser and Goes to Website */
    public void showWebsite(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.team1294.org")));
    }
    /* Opens User's Browser and Goes to Team Facebook */
    public void showFacebook(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/topgunrobotics")));
    }
    /* Opens User's Browser and Goes to Team YouTube */
    public void showYouTube(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/user/TopGun1294")));
    }
    /* Opens User's Browser and Goes to Team Google Plus */
    public void showGPlus(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://plus.google.com/103733199754924235388/posts")));
    }
    /* Opens User's Browser and Goes to GitHub */
    public void showGitHub(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://github.com/FRC-1294")));
    }
    /* Opens About Activity */
    public void showAbout(View v){
        startActivity(new Intent(this, AboutActivity.class));
    }
    /* Opens Sponsors Activity*/
    public void showSponsors(View v){
        startActivity(new Intent(this, SponsorActivity.class));
    }
    /* Opens User's Browser and Goes to FRC Kickoff Livestream (Real Link to be Added Later) */
    public void showLivestream(View v){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.usfirst.org/roboticsprograms/frc/kickoff")));
    }
    public void navigateToEvent(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(currentEvent.getLocation()));
        startActivity(intent);
    }

    public void expandTimer(View v) {
        final Intent i =new Intent(this, TimerActivity.class);
        //i.putExtra("event", currentEvent);
        v.postDelayed(new Runnable() {
              @Override
              public void run() {
                  startActivity(i);
              }
          },
         300L);
    }

    public void expandEvent(View v) {
        /*final Intent i =new Intent(this, TimerActivity.class);
        v.postDelayed(new Runnable() {
                          @Override
                          public void run() {
                              startActivity(i);
                          }
                      },
                300L);*/
    }
}