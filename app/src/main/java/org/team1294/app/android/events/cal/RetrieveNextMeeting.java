package org.team1294.app.android.events.cal;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import org.team1294.app.android.fonts.Fonts;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RetrieveNextMeeting extends AsyncTask<Void, Void, List<Event>> {
    private Activity context;
    private int daysTillId;
    private int timePlaceId;
    private View holder;
    private View innerHolder;
    private ProgressBar loadingSpinner;

    public RetrieveNextMeeting(Context context, int holderId, int innerHolderId, int daysTillId, int timePlaceId, int spinnerId){
        this.context = (Activity) context;
        this.daysTillId = daysTillId;
        this.timePlaceId = timePlaceId;

        this.holder = this.context.findViewById(holderId);
        this.innerHolder = this.context.findViewById(innerHolderId);
        this.loadingSpinner = (ProgressBar) this.context.findViewById(spinnerId);

        innerHolder.setVisibility(View.GONE);
        loadingSpinner.setVisibility(View.VISIBLE);
    }

    protected List<Event> doInBackground(Void... v) {
        try {
            List<Event> tempList = new CalendarService(context).setup()
                    .events()
                    .list("frc1294@gmail.com")
                    .setMaxResults(1)
                    .setQ("Build Season")
                    // .setQ("Regular Meeting")
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .setShowDeleted(false)
                    .setTimeMin(new DateTime(new Date()))
                    .execute()
                    .getItems();
            if(tempList.size() == 0){
                return null;
            }
            return tempList;
        } catch (IOException e) {
            Log.e("1294", "Calendar Error: ", e);
            return null;
        }
    }

    protected void onPostExecute(List<Event> events){
        if(events == null) return;

        Event event = events.get(0);

        try {
            TextView viewTextTimeLocation = (TextView) context.findViewById(timePlaceId);

            Calendar calStart = Calendar.getInstance(),
                              calEnd = Calendar.getInstance(),
                              calNow = Calendar.getInstance();
            DateTime startTime, endTime;
            SimpleDateFormat format;
            boolean flag = true;
            if(event.getStart().getDateTime() == null){
                startTime = event.getStart().getDate();
                endTime = event.getEnd().getDate();
                format = new SimpleDateFormat("yyyy-MM-dd");
                viewTextTimeLocation.setVisibility(View.GONE);
                flag = false;
            }else{
                startTime = event.getStart().getDateTime();
                endTime = event.getEnd().getDateTime();
                format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSZ");
            }


            Date start = format.parse(startTime.toStringRfc3339());
            calStart.setTime(start);

            Date end = format.parse(endTime.toStringRfc3339());
            calEnd.setTime(end);

            TextView viewsTextDaysTill = (TextView) context.findViewById(daysTillId);
            viewsTextDaysTill.setTypeface(new Fonts(context).robotoThin);
            viewsTextDaysTill.setText(
                    String.valueOf(
                            Math.abs(
                                    (calStart.getTimeInMillis() - calNow.getTimeInMillis()) / 86400000
                            )
                    )
            );

            if(flag) {
                String tempStart = String.valueOf(calStart.get(Calendar.HOUR));
                String tempEnd = String.valueOf(calEnd.get(Calendar.HOUR) + (calEnd.get(Calendar.AM_PM) == Calendar.AM ? " AM" : " PM"));
                String location = event.getLocation().equals("Eastlake High School, 400 228th Ave NE, Sammamish, WA, United States") ? "Eastlake High School" : event.getLocation();
                viewTextTimeLocation.setText(tempStart + " to " + tempEnd + " at " + location);
            }
            innerHolder.setVisibility(View.VISIBLE);
            loadingSpinner.setVisibility(View.GONE);
        } catch (ParseException e) {
            Log.e("1294", "Calendar Error: ", e);
            holder.setVisibility(View.GONE);
        }
    }
}
