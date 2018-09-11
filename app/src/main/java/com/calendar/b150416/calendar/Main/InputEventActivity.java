package com.calendar.b150416.calendar.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.calendar.b150416.calendar.Calendar.CalendarView;
import com.calendar.b150416.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class InputEventActivity extends AppCompatActivity{
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

    }
    
}
