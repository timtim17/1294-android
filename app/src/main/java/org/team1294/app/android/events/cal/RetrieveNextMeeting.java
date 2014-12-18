package org.team1294.app.android.events.cal;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import org.team1294.app.android.fonts.Fonts;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RetrieveNextMeeting extends AsyncTask<Void, Void, List<Event>> {
    private Activity context;
    private int daysTillId;
    private int timePlaceId;
    private View holder;

    public RetrieveNextMeeting(Context context, int holderId, int daysTillId, int timePlaceId){
        this.context = (Activity) context;
        this.daysTillId = daysTillId;
        this.timePlaceId = timePlaceId;

        this.holder = this.context.findViewById(holderId);
    }

    protected List<com.google.api.services.calendar.model.Event> doInBackground(Void... v) {
        try {
            return new CalendarService(context).setup()
                    .events()
                    .list("frc1294@gmail.com")
                    .setMaxResults(1)
                    .setQ("Regular Meeting")
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .setShowDeleted(false)
                    .setTimeMin(new DateTime(new Date()))
                    .execute()
                    .getItems();
        } catch (IOException e) {
            Log.e("1294", "Calendar Error: ", e);
            cancel(true);
            holder.setVisibility(View.GONE);
            return null;
        }
    }

    protected void onPostExecute(List<com.google.api.services.calendar.model.Event> events){
        com.google.api.services.calendar.model.Event event = events.get(0);

        TextView viewTextTimeLocation = (TextView) context.findViewById(timePlaceId);

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSZ");

            Calendar calStart = GregorianCalendar.getInstance();
            Date start = format.parse(event.getStart().getDateTime().toStringRfc3339());
            calStart.setTime(start);

            Calendar calEnd = GregorianCalendar.getInstance();
            Date end = format.parse(event.getEnd().getDateTime().toStringRfc3339());
            calEnd.setTime(end);

            Calendar calNow = GregorianCalendar.getInstance();

            TextView viewsTextDaysTill = (TextView) context.findViewById(daysTillId);
            viewsTextDaysTill.setTypeface(new Fonts(context).robotoThin);
            viewsTextDaysTill.setText(String.valueOf(calStart.get(Calendar.DAY_OF_YEAR) - calNow.get(Calendar.DAY_OF_YEAR)));

            String tempStart = String.valueOf(calStart.get(Calendar.HOUR));
            String tempEnd = String.valueOf(calEnd.get(Calendar.HOUR) + (calEnd.get(Calendar.AM_PM) == Calendar.AM ? " AM" : " PM"));
            String location = event.getLocation().equals("Eastlake High School, 400 228th Ave NE, Sammamish, WA, United States") ? "Eastlake High School" : event.getLocation();
            viewTextTimeLocation.setText(tempStart + " to " +tempEnd + " at " + location);
        } catch (ParseException e) {
            Log.e("1294", "Calendar Error: " + e.getMessage(), e);
            holder.setVisibility(View.GONE);
        }
    }
}
