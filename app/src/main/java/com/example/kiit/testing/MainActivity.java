package com.example.kiit.testing;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
            cusmtomAdapter adapter;
            TextView task,display;
            DBHelper mydb;
            ListView listview;
            CheckBox checkBox;
            dailog dialog;
            TimePicker timer;
            alarmReciever alarm;
            static int global_int;
            Alarmui alarmui;
            static MainActivity activity1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        listview = findViewById(R.id.listview);
        checkBox = findViewById(R.id.chekbox);
        timer = findViewById(R.id.timePicker);
        display = findViewById(R.id.alarmdisplay);
        adapter = new cusmtomAdapter(mydb.listfromdb(), this);
        listview.setAdapter(adapter);
        registerForContextMenu(listview);
        dialog = new dailog();
        alarm = new alarmReciever();
        alarmui = new Alarmui();
        activity1 = this;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Return");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Set Alarm");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        global_int = info.position;

       if (item.getTitle().equals("Delete")) {
           TextView textView = findViewById(R.id.textview1);
           Customers customers = (Customers) listview.getItemAtPosition(global_int);
           String text1 = (String) String.valueOf(customers.getFirstName());
          adapter.remove(global_int);
            mydb.deleteData(text1);
            adapter.notifyDataSetChanged();
        }else if (item.getTitle().equals("Set Alarm")){
            dialog.showdialog(MainActivity.this,"Set Alarm");
        }

        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                final EditText popup = new EditText(this);
                int maxLength = 35;
                popup.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                AlertDialog dialog1 = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What would you like to do")
                        .setView(popup)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String adder = popup.getText().toString();
                                adapter.add(new Customers(adder,null));
                                mydb.insertData(adder);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog1.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public String textgetter() {

                        TextView textView = findViewById(R.id.textview1);
                        Customers customers = (Customers) listview.getItemAtPosition(global_int);
                        String text = (String) String.valueOf(customers.getFirstName());
                        return text;
    }

    public static MainActivity getInstance1(){
        return   activity1;
    }
        }