package org.team1294.app.android.events;

import java.util.Date;

public class Events {

    public static Event frcKickOff = null;

    public Events(){
        if(frcKickOff == null) {
            /* Create a new Event object that contains the details of the FRC Kickoff on 01/03/2015 @ 7:30 AM PST */
            frcKickOff = new Event("FRC Kickoff", "Mountlake Terrace High School", new Date(115, 0, 2, 15, 30));
        }
    }
}
