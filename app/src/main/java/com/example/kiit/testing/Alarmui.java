package com.example.kiit.testing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class Alarmui extends AppCompatActivity {

    Button cancel;
    alarmReciever alarmReciever1;
    TextView display, some;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmui);
        alarmReciever1 = new alarmReciever();
        cancel = (Button) findViewById(R.id.alarmCancel);
        display = findViewById(R.id.alarmdisplay);
        listView = findViewById(R.id.listview);
        some = findViewById(R.id.textview1);
       String text =  MainActivity.getInstance1().textgetter();
            display.setText(text);
        cancelbutton();
    }


    public void cancelbutton() {
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alarmReciever1 = new alarmReciever();
                        alarmReciever.cancelAlarm(getApplicationContext());
                        alarmReciever.alarmstoper();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}