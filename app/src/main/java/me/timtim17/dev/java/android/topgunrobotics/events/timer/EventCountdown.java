package me.timtim17.dev.java.android.topgunrobotics.events.timer;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import me.timtim17.dev.java.android.topgunrobotics.R;
import me.timtim17.dev.java.android.topgunrobotics.events.Event;

public class EventCountdown {
    private CountDownTimer countDownTimer;

    public EventCountdown(Event event, final TextView timerText, final View viewToShow, final boolean hideCountdownOnDone){
        this.countDownTimer = new CountDownTimer(event.getTimeInMillis() -System.currentTimeMillis(), 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int days = 0, hours = 0, minutes = 0, seconds = 0;

                while(millisUntilFinished >= 86400000){
                    days++;
                    millisUntilFinished -= 86400000;
                }
                while(millisUntilFinished >= 3600000){
                    hours++;
                    millisUntilFinished -= 36000000;
                }
                while(millisUntilFinished >= 60000){
                    minutes++;
                    millisUntilFinished -= 60000;
                }
                while(millisUntilFinished >= 1000){
                    seconds++;
                    millisUntilFinished -=1000;
                }

                String timeString = "";

                if(days < 10){timeString += "0" + days + ":";}else if(days >= 10){timeString += String.valueOf(days) + ":";}
                if(hours < 10){timeString += "0" + hours + ":";}else if(days >= 10){timeString += String.valueOf(hours) + ":";}
                if(minutes < 10){timeString += "0" + minutes + ":";}else if(minutes >= 10){timeString += String.valueOf(minutes) + ":";}
                if(seconds < 10){timeString += "0" + seconds;}else if(seconds >= 10){timeString += String.valueOf(seconds);}
                timerText.setText(timeString);
                if(minutes <= 1 && !(viewToShow.getVisibility() == View.VISIBLE)){
                    viewToShow.setVisibility(View.VISIBLE);
                }
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
                        if(hideCountdownOnDone){timerText.setVisibility(View.GONE);}
                    }
                }.start();
            }
        }.start();
    }

    public void stop(){
        countDownTimer.cancel();
    }
}
