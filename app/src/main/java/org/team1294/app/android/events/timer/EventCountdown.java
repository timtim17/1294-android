package org.team1294.app.android.events.timer;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.team1294.app.android.R;
import org.team1294.app.android.events.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventCountdown {
    private TextView timerText;

    public EventCountdown(Event event, final View timerView, int timerTextId, int eventId, final View viewToShow, final boolean hideCountdownOnDone){
        long eventTime = event.getDate().getTime(),
                 nowTime = new Date(System.currentTimeMillis()).getTime();
        timerText = (TextView) timerView.findViewById(timerTextId);
        TextView eventText = (TextView) timerView.findViewById(eventId);
        eventText.setText("Till " + event.getEventName());
        new CountDownTimer(eventTime - nowTime, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Date date = new Date(millisUntilFinished);

                int seconds = Integer.parseInt(new SimpleDateFormat("ss").format(date)),
                    minutes = Integer.parseInt(new SimpleDateFormat("mm").format(date)),
                    hours = Integer.parseInt(new SimpleDateFormat("HH").format(date)),
                    days = Integer.parseInt(new SimpleDateFormat("DD").format(date));

                String timeString = "";

                if(days < 10){timeString += "0" + days + ":";}else if(days >= 10){timeString += String.valueOf(days) + ":";}
                if(hours < 10){timeString += "0" + hours + ":";}else if(days >= 10){timeString += String.valueOf(hours) + ":";}
                if(minutes < 10){timeString += "0" + minutes + ":";}else if(minutes >= 10){timeString += String.valueOf(minutes) + ":";}
                if(seconds < 10){timeString += "0" + seconds;}else if(seconds >= 10){timeString += String.valueOf(seconds);}
                timerText.setText(timeString);
                if((days == 0 && hours == 0 &&minutes <= 1) &&  !(viewToShow.getVisibility() == View.VISIBLE)){
                    viewToShow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish() {
                viewToShow.setVisibility(View.VISIBLE);
                timerText.setTextColor(timerText.getResources().getColor(R.color.timer_over));
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished){
                        if(timerText.getVisibility() == View.VISIBLE){
                            timerText.setVisibility(View.INVISIBLE);
                        }else{
                            timerText.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFinish(){
                        if(hideCountdownOnDone){timerView.setVisibility(View.GONE);}
                    }
                }.start();
            }
        }.start();
    }

    public EventCountdown(Event event, final View timerView, int timerTextId, int eventId, final boolean hideCountdownOnDone){
        long eventTime = event.getDate().getTime(),
                nowTime = new Date(System.currentTimeMillis()).getTime();
        timerText = (TextView) timerView.findViewById(timerTextId);
        TextView eventText = (TextView) timerView.findViewById(eventId);
        eventText.setText("Till " + event.getEventName());
        new CountDownTimer(eventTime - nowTime, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Date date = new Date(millisUntilFinished);

                int seconds = Integer.parseInt(new SimpleDateFormat("ss").format(date)),
                        minutes = Integer.parseInt(new SimpleDateFormat("mm").format(date)),
                        hours = Integer.parseInt(new SimpleDateFormat("HH").format(date)),
                        days = Integer.parseInt(new SimpleDateFormat("DD").format(date));

                String timeString = "";

                if(days < 10){timeString += "0" + days + ":";}else if(days >= 10){timeString += String.valueOf(days) + ":";}
                if(hours < 10){timeString += "0" + hours + ":";}else if(days >= 10){timeString += String.valueOf(hours) + ":";}
                if(minutes < 10){timeString += "0" + minutes + ":";}else if(minutes >= 10){timeString += String.valueOf(minutes) + ":";}
                if(seconds < 10){timeString += "0" + seconds;}else if(seconds >= 10){timeString += String.valueOf(seconds);}
                timerText.setText(timeString);
            }

            @Override
            public void onFinish() {
                timerText.setTextColor(timerText.getResources().getColor(R.color.timer_over));
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished){
                        if(timerText.getVisibility() == View.VISIBLE){
                            timerText.setVisibility(View.INVISIBLE);
                        }else{
                            timerText.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFinish(){
                        if(hideCountdownOnDone){timerView.setVisibility(View.GONE);}
                    }
                }.start();
            }
        }.start();
    }
}
