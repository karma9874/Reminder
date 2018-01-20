package com.example.kiit.testing;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;
import java.util.Calendar;

public class alarmReciever extends BroadcastReceiver {
    static MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        mediaPlayer= MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
        Toast.makeText(context,"Alarm Set",Toast.LENGTH_LONG).show();
        notifihandler(context);
        Intent intent1 = new Intent(context,Alarmui.class);
        context.startActivity(intent1);

    }

    public static void alarmstoper(){
        mediaPlayer.stop();
    }

    public static void setAlarm(Context context, Calendar targetcalendar){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context,alarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,targetcalendar.getTimeInMillis(),pendingIntent);
    }

    public static void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context,alarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.cancel(pendingIntent);
    }

    public static void notifihandler(Context context){

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker("this is tikcer ");
        builder.setContentTitle(MainActivity.getInstance1().textgetter());
        builder.setContentText("soemthing");
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(011,builder.build());
    }
}
