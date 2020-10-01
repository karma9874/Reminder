package com.example.kiit.testing;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;


public class dailog  {
    TimePicker timer;
    Calendar calendar;



    public void showdialog(final Activity activity, String msg) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        Button setbutton = (Button) dialog.findViewById(R.id.savebutton);
        timer = (TimePicker) dialog.findViewById(R.id.timePicker);
        setbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,timer.getCurrentHour());
                        calendar.set(Calendar.MINUTE,timer.getCurrentMinute());

                        int hour =  (int)timer.getCurrentHour();
                        int min = (int)timer.getCurrentMinute();

                        String shour = hour+"";
                        String smin = min+"";

                        Toast.makeText(activity, "Alarm Set", Toast.LENGTH_LONG).show();

                            alarmReciever.setAlarm(activity,calendar);

                        dialog.dismiss();
                    }
                }
        );


    Button cancelButton = (Button) dialog.findViewById(R.id.cancelbutton);
    cancelButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            }
    );

    dialog.show();
}



}




