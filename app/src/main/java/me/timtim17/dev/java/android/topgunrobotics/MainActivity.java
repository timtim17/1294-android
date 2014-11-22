package me.timtim17.dev.java.android.topgunrobotics;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;
import me.timtim17.dev.java.android.topgunrobotics.about.AboutActivity;
import me.timtim17.dev.java.android.topgunrobotics.events.Event;
import me.timtim17.dev.java.android.topgunrobotics.events.Events;
import me.timtim17.dev.java.android.topgunrobotics.events.timer.EventCountdown;
import me.timtim17.dev.java.android.topgunrobotics.fonts.Fonts;
import me.timtim17.dev.java.android.topgunrobotics.sponsors.SponsorActivity;
import me.timtim17.dev.java.android.topgunrobotics.events.timer.TimerActivity;


public class MainActivity extends ActionBarActivity {
    // TODO: Create JavaDoc Comments
    // TODO: Add Next Event Card

    private EventCountdown eventCountdown;
    private Event currentEvent = new Events().frcKickOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timerView = (TextView) findViewById(R.id.timer);
        Typeface robotoThin = new Fonts(this).robotoThin;
        timerView.setTypeface(robotoThin);
        TextView eventText = (TextView) findViewById(R.id.event);
        LinearLayout afterCountdownDoneShowView = (LinearLayout) findViewById(R.id.layout_livestream);
        eventText.setText("Till " + currentEvent.getEventName());
        eventCountdown = new EventCountdown(currentEvent, timerView, afterCountdownDoneShowView, true);
    }

    @Override
    protected void onDestroy(){
        eventCountdown.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    /* Opens User's Browser and Goes to FRC Kickoff Livestream (Link to be Added Later) */
    // TODO: Add link to livestream.
    public void showLivestream(View v){
        /*startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("")));*/
    }
    public void navigateToEvent(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=Mountlake+Terrace+High+School"));
        startActivity(intent);
    }

   public void expandTimer(View v){startActivity(new Intent(this, TimerActivity.class));}
}