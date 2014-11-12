package me.timtim17.dev.java.android.topgunrobotics;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    // TODO: Create JavaDoc Comments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    /* Opens User's Browser and Goes to About */
    public void showAbout(View v){
        startActivity(new Intent(this, AboutActivity.class));
    }
}