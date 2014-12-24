package org.team1294.app.android.events;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Events {
    private static Event[] events = {
            new Event("FRC Kickoff", "Mountlake Terrace High School", new GregorianCalendar(2015, 0, 3, 7, 30)),
            new Event("Bag & Tag", "Eastlake High School", new GregorianCalendar(2015, 1, 17, 21, 0))
    };

    public static Event getNextEvent(){
        Calendar nowTime = Calendar.getInstance(),
                          eventTime = Calendar.getInstance();

        for(int i = 0; i < events.length; i++){
            eventTime.setTimeInMillis(events[i].getDate().getTimeInMillis());
            eventTime.add(Calendar.MINUTE, 5);
            if(nowTime.getTimeInMillis() < eventTime.getTimeInMillis()) return events[i];
        }
        return null;
    }
}
