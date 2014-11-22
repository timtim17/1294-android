package me.timtim17.dev.java.android.topgunrobotics.events;

import java.util.Calendar;

public class Events {
    private  Calendar c = Calendar.getInstance();

    public  Event frcKickOff;

    public Events(){
        /* Create a new Event object that contains the details of the FRC Kickoff on 01/03/2015 @ 7:30 AM PST */
        c.set(Calendar.YEAR, 2015);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 3);
        c.set(Calendar.HOUR_OF_DAY, 7);
        c.set(Calendar.MINUTE, 30);
        frcKickOff = new Event("FRC Kickoff", null, c);
    }
}
