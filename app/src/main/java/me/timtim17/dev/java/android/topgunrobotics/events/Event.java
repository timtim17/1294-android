package me.timtim17.dev.java.android.topgunrobotics.events;

import android.net.Uri;
import java.util.Calendar;

public class Event {
    private String eventName;
    private Uri location;
    private Calendar dateTime;

    public Event(String eventName, Uri eventLocation, Calendar eventTime){
        this.eventName = eventName;
        this.location = eventLocation;
        this.dateTime = eventTime;
    }

    @Override
    public String toString(){
        return this.eventName;
    }

    public String getEventName(){
        return this.eventName;
    }
    public Uri getLocation(){
        return this.location;
    }
    public  long getTimeInMillis(){
        return this.dateTime.getTimeInMillis();
    }
}
