package com.calendar.b150416.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner yearSpinner;
    Spinner monthSpinner;
    ArrayList<Integer> yearList;
    ArrayList<Integer> monthList;
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        calendarView.setEventManager(new EventManagerAdapter());

        yearSpinner=(Spinner)findViewById(R.id.yearSpinner);
        monthSpinner=(Spinner)findViewById(R.id.monthSpinner);
        yearList=new ArrayList<Integer>();
        monthList=new ArrayList<Integer>();

        for(int i=1901;i<=2099;++i)
            yearList.add(i);

        for(int i=1;i<=12;++i)
            monthList.add(i);

        ArrayAdapter<Integer> yearadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, yearList);
        yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearadapter);
        yearSpinner.setSelection(calendarView.displayDate.get(Calendar.YEAR)-1901);
        yearSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<Integer> monthadapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, monthList);
        monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthadapter);
        monthSpinner.setSelection(calendarView.displayDate.get(Calendar.MONTH));
        monthSpinner.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.yearSpinner:
                int newYear=position+1901;
                calendarView.setYear(newYear);
                calendarView.postInvalidate();
                break;
            case R.id.monthSpinner:
                int newMonth=position;
                calendarView.setMonth(newMonth);
                calendarView.postInvalidate();
                break;
        }
        Log.i("onItemSelected","onItemSelected");
    }

    /**
     * 没有数据的时候执行
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

