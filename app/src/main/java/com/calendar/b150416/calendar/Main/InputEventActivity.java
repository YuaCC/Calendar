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

public class InputEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner daySpinner;
    Spinner hourSpinner;
    Spinner minuteSpinner;
    Calendar cal;
    ArrayAdapter<Integer> yearadapter;
    ArrayAdapter<Integer> monthadapter;
    ArrayAdapter<Integer> dayadapter;
    ArrayAdapter<Integer> houradapter;
    ArrayAdapter<Integer> minuteadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        yearSpinner=(Spinner)findViewById(R.id.input_year_spinner);
        monthSpinner=(Spinner)findViewById(R.id.input_month_spinner);
        daySpinner=(Spinner)findViewById(R.id.input_day_spinner);
        hourSpinner=(Spinner)findViewById(R.id.input_hour_spinner);
        minuteSpinner=(Spinner)findViewById(R.id.input_minute_spinner);

        ArrayList<Integer> yearList=new ArrayList<Integer>();
        ArrayList<Integer> monthList=new ArrayList<Integer>();
        ArrayList<Integer> dayList=new ArrayList<Integer>();
        ArrayList<Integer> hourList=new ArrayList<Integer>();
        ArrayList<Integer> minuteList=new ArrayList<Integer>();

        cal=Calendar.getInstance();
        cal.setTime(new Date());
        Intent intent=getIntent();
        int year=intent.getIntExtra("year",cal.get(Calendar.YEAR));
        int month=intent.getIntExtra("month",cal.get(Calendar.MONTH));
        int day=intent.getIntExtra("day",cal.get(Calendar.DAY_OF_MONTH));
        int hour=intent.getIntExtra("hour",cal.get(Calendar.HOUR_OF_DAY));
        int minute=intent.getIntExtra("minute",cal.get(Calendar.MINUTE));
        String description=intent.getStringExtra("description");
        EditText editText=(EditText)findViewById(R.id.input_event_description);
        editText.setText(description);

        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);
        int daycnt= CalendarView.getDaysOfMonth(cal);

        for(int i=1901;i<=2049;++i) yearList.add(i);
        for(int i=1;i<=12;++i)  monthList.add(i);
        for(int i=1;i<=daycnt;++i)  dayList.add(i);
        for(int i=0;i<24;++i) hourList.add(i);
        for(int i=0;i<60;++i)minuteList.add(i);

        yearadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, yearList);
        yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearadapter);
        yearSpinner.setSelection(year-1901);
        yearSpinner.setOnItemSelectedListener(this);

        monthadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, monthList);
        monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthadapter);
        monthSpinner.setSelection(month);
        monthSpinner.setOnItemSelectedListener(this);

        dayadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, dayList);
        dayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayadapter);
        daySpinner.setSelection(day-1);
        daySpinner.setOnItemSelectedListener(this);

        houradapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, hourList);
        houradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(houradapter);
        hourSpinner.setSelection(hour);
        hourSpinner.setOnItemSelectedListener(this);

        minuteadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, minuteList);
        minuteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(minuteadapter);
        minuteSpinner.setSelection(minute);
        minuteSpinner.setOnItemSelectedListener(this);

        Button saveButton=(Button)findViewById(R.id.input_event_save);
        Button nosaveButton=(Button)findViewById(R.id.input_event_nosave);
        Button deleteButton=(Button)findViewById(R.id.input_event_delete);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int hour=cal.get(Calendar.HOUR_OF_DAY);
                int minute=cal.get(Calendar.MINUTE);
                EditText editText=(EditText)findViewById(R.id.input_event_description);
                intent.putExtra("year",cal.get(Calendar.YEAR));
                intent.putExtra("month",cal.get(Calendar.MONTH));
                intent.putExtra("day",cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("hour",cal.get(Calendar.HOUR_OF_DAY));
                intent.putExtra("minute",cal.get(Calendar.MINUTE));
                intent.putExtra("description",editText.getText().toString());
                setResult(0,intent);
                finish();
            }
        });
        nosaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int hour=cal.get(Calendar.HOUR_OF_DAY);
                int minute=cal.get(Calendar.MINUTE);
                EditText editText=(EditText)findViewById(R.id.input_event_description);
                intent.putExtra("year",cal.get(Calendar.YEAR));
                intent.putExtra("month",cal.get(Calendar.MONTH));
                intent.putExtra("day",cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("hour",cal.get(Calendar.HOUR_OF_DAY));
                intent.putExtra("minute",cal.get(Calendar.MINUTE));
                intent.putExtra("description",editText.getText().toString());
                setResult(1,intent);
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int hour=cal.get(Calendar.HOUR_OF_DAY);
                int minute=cal.get(Calendar.MINUTE);
                EditText editText=(EditText)findViewById(R.id.input_event_description);
                intent.putExtra("year",cal.get(Calendar.YEAR));
                intent.putExtra("month",cal.get(Calendar.MONTH));
                intent.putExtra("day",cal.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("hour",cal.get(Calendar.HOUR_OF_DAY));
                intent.putExtra("minute",cal.get(Calendar.MINUTE));
                intent.putExtra("description",editText.getText().toString());
                setResult(2,intent);
                finish();
            }
        });
    }
    void adaptDaySpinner(){
        int newdaycnt=CalendarView.getDaysOfMonth(cal);
        int olddaycnt=dayadapter.getCount();
        if(newdaycnt>olddaycnt){
            for(int i=olddaycnt+2;i<=newdaycnt;++i)
                dayadapter.add(olddaycnt);
        }else if(newdaycnt<olddaycnt){
            if(daySpinner.getSelectedItemPosition()>newdaycnt-1)
                daySpinner.setSelection(newdaycnt-1);
            for(int i=newdaycnt+2;i<=olddaycnt;++i)
                dayadapter.remove(i);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.input_year_spinner:
                cal.set(Calendar.YEAR,position+1901);
                adaptDaySpinner();
                break;
            case R.id.input_month_spinner:
                cal.set(Calendar.MONTH,position);
                adaptDaySpinner();
                break;
            case R.id.input_day_spinner:
                cal.set(Calendar.DAY_OF_MONTH,position+1);
                break;
            case R.id.input_hour_spinner:
                cal.set(Calendar.HOUR_OF_DAY,position);
                break;
            case R.id.input_minute_spinner:
                cal.set(Calendar.MINUTE,position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
