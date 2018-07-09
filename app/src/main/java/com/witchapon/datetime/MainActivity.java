package com.witchapon.datetime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView tView,datepick;
    Date opentime;
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                while (!thread.isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Date currentTime = Calendar.getInstance().getTime();
                            long diff = currentTime.getTime() - opentime.getTime();
                            long diffSeconds = diff / 1000 % 60;
                            long diffMinutes = diff / (60 * 1000) % 60;
                            long diffHours = diff / (60 * 60 * 1000)%60;
                            SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
                            tView.setText(sf.format(currentTime));
                            datepick.setText( +diffHours+" ชั่วโมง "+diffMinutes+" นาที "+diffSeconds+" วินาที");

                        }
                    });
                }
            } catch (InterruptedException e) {
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.textview1);
        datepick = (TextView) findViewById(R.id.datepick);


        opentime = Calendar.getInstance().getTime();
        Log.d("Dattime", "onCreate: "+opentime.toString());
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        tView.setText(sf.format(opentime));

        thread.start();





    }

}
