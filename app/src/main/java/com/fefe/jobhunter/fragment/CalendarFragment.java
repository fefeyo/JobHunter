package com.fefe.jobhunter.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fefe.jobhunter.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

/**
* A simple {@link Fragment} subclass.
*/
public class CalendarFragment extends Fragment {
    private CalendarPickerView mCalendar;

    public CalendarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        mCalendar = (CalendarPickerView)v.findViewById(R.id.calendar);
        setCalendar();
        return v;
    }

    private void setCalendar(){
        Date today = new Date();
        Calendar max = Calendar.getInstance();
        max.add(Calendar.YEAR, 1);
        mCalendar.init(
                today,
                max.getTime()
        ).withSelectedDate(today);
        mCalendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
        mCalendar.setOnInvalidDateSelectedListener(new CalendarPickerView.OnInvalidDateSelectedListener() {
            @Override
            public void onInvalidDateSelected(Date date) {

            }
        });
    }
}