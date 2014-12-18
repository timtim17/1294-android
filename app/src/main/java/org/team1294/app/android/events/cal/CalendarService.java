package org.team1294.app.android.events.cal;

import android.app.Activity;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

public class CalendarService {
    Activity context;

    public CalendarService(Activity a){
        context = a;
    }

    public Calendar setup(){
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        //String devKey = "AIzaSyCsuertlnh87gNDuh4RvqHFw7vZEdangDM";
        //CalendarRequestInitializer apiKeyInit = new CalendarRequestInitializer(devKey);

        GoogleCredential credential;
        try {
            InputStream initialStream = context.getAssets().open("key.p12");
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            File file = File.createTempFile("key", ".p12");
            OutputStream outStream = new FileOutputStream(file);
            outStream.write(buffer);

            credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(jsonFactory)
                    .setServiceAccountId("515916623267-ll1752t9k03g6rs4t9spkojgr6ioi3t8@developer.gserviceaccount.com")
                    .setServiceAccountPrivateKeyFromP12File(file)
                    .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR_READONLY))
                    .build();
        }catch(Exception e) {
            Log.e("1294", "Calendar Error: ", e);
            return null;
        }

        return new Calendar.Builder(httpTransport, jsonFactory, credential)
                //.setCalendarRequestInitializer(apiKeyInit)
                .setApplicationName("Top Gun Robotics")
                .build();
    }
}
