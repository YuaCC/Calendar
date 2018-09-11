package com.calendar.b150416.calendar;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner yearSpinner;
    Spinner monthSpinner;
    ArrayList<Integer> yearList;
    ArrayList<Integer> monthList;
    CalendarView calendarView;
    EventManagerAdapter ema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        ListView listView=(ListView)findViewById(R.id.eventList);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,new LinkedList<String>());
        arrayAdapter.add("添加新的事项");
        ema=new EventManagerAdapter(arrayAdapter,MainActivity.this);
        calendarView.setEventManager(ema);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent(MainActivity.this,InputEventActivity.class);

                if(position<ema.datas.size()){
                    Event event=ema.getEventByPosition(position);
                    i.putExtra("year",event.year);
                    i.putExtra("month",event.month);
                    i.putExtra("day",event.day);
                    i.putExtra("hour",event.hour);
                    i.putExtra("minute",event.minute);
                    i.putExtra("description",event.description);
                }

                startActivityForResult(i,position);
            }
        });

        yearSpinner=(Spinner)findViewById(R.id.yearSpinner);
        monthSpinner=(Spinner)findViewById(R.id.monthSpinner);
        yearList=new ArrayList<Integer>();
        monthList=new ArrayList<Integer>();

        for(int i=1901;i<=2049;++i)
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//数据拿回来需要一个判断
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {//拿回来的数据不为空
            int year=data.getIntExtra("year",1901);
            int month=data.getIntExtra("month",0);
            int day=data.getIntExtra("day",1);
            int hour=data.getIntExtra("hour",0);
            int minute=data.getIntExtra("minute",0);
            String description=data.getStringExtra("description");
            Event e=new Event(year,month,day,hour,minute,description);
            switch (resultCode){
                case 0:
                    if(requestCode<ema.datas.size())
                        ema.changeEvent(ema.getEventByPosition(requestCode),e);
                    else
                        ema.addEvent(e);
                    break;
                case 1:
                    break;
                case 2:
                    ema.removeEvent(ema.getEventByPosition(requestCode));
                    break;
            }
        }

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
                calendarView.setMonth(position);
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
    @Override
    protected void onStop(){
        super.onStop();
        ema.save();
    }

}

