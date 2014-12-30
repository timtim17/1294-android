package org.team1294.app.android.events;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event{
    private String eventName;
    private String mapsURL;
    private Date date;

    private String locationName;

    private SimpleDateFormat sf = new SimpleDateFormat("EEEE',' MMMM d yyyy '@' h':'mm a");

    public Event(String eventName, String location, Date cal){
        this.eventName = eventName;
        this.locationName = location;
        try {
            this.mapsURL = "geo:0,0?q=" + URLEncoder.encode(location, "UTF-8");
        }catch(UnsupportedEncodingException e){
            Log.e("1294", "Events Error: " + e.getMessage());
            this.mapsURL = null;
        }
        this.date = cal;
    }

    @Override
    public String toString(){
        return this.eventName
                        + ": "
                        + sf.format(this.date)
                        + " at "
                        + this.locationName;
    }

    public String getEventName(){
        return this.eventName;
    }
    public String getLocation(){return this.mapsURL;}
    public Date getDate(){
        return this.date;
    }
}
