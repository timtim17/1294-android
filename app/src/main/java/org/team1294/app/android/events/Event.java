package org.team1294.app.android.events;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

public class Event{
    private String eventName;
    private String mapsURL;
    private Date date;

    public Event(String eventName, String location, Date cal){
        this.eventName = eventName;
        try {
            this.mapsURL = "geo:0,0?q=" + URLEncoder.encode(location, "UTF-8");
        }catch(UnsupportedEncodingException e){
            Log.e("1294", "Events Error: " + e.getMessage());
        }
        this.date = cal;
    }

    @Override
    public String toString(){
        return this.eventName;
    }

    public String getEventName(){
        return this.eventName;
    }
    public String getLocation(){return this.mapsURL;}
    public Date getDate(){
        return this.date;
    }
}
