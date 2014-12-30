package org.team1294.app.android.events;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Events {
    private static Event[] events = {
            new Event("FRC Kickoff 2015", "Mountlake Terrace High School", new Date(115, 0, 3, 7, 15)),    // FRC Kickoff 2015: January 3rd 2015 @ 7:15 AM
            new Event("Bag & Tag", "Eastlake High School", new Date(115, 1, 17, 21, 0))                                   // Bag & Tag: February 17th, 2015 @ 9:00 PM
    };

    public static Event getNextEvent(){
        Calendar nowTime = Calendar.getInstance(),
                          eventTime = Calendar.getInstance();

        //*
            // Event Test Code
            // Reads each event and prints it's details to LogCat

            for(Event e : events) {
                Log.v("1294", e.toString());
            }

        //*/

        for(int i = 0; i < events.length; i++){
            eventTime.setTimeInMillis(events[i].getDate().getTime());
            eventTime.add(Calendar.MINUTE, 5);
            if(nowTime.getTimeInMillis() < eventTime.getTimeInMillis()) return events[i];
        }
        return null;
    }
}
