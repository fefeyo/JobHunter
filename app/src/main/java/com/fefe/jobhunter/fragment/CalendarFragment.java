package com.fefe.jobhunter.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.CalendarDetailActivity;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.Data;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
//                final Intent i = new Intent(getActivity(), CalendarDetailActivity.class);
//                /**
//                 * 月日を検索条件にいれる
//                 * DBの見直しが必要・・・？
//                 */
//                final List<Data> data = new Select().from(Data.class).where("").execute();
//                startActivity(i);
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("カレンダー");
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private boolean isexistEvent(int month, int day){
        final List<Data> dataList = new Select().from(Data.class).where("").execute();
        return false;
    }

}