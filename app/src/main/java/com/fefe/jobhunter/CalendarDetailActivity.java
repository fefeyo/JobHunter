package com.fefe.jobhunter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.FontAwesomeText;


public class CalendarDetailActivity extends ActionBarActivity {
    private ScrollView calendarDetailBack;
    private TextView calendarDate;
    private LinearLayout calendarDetailContainer;

    private void assignViews() {
        calendarDetailBack = (ScrollView) findViewById(R.id.calendar_detail_back);
        calendarDate = (TextView) findViewById(R.id.calendar_date);
        calendarDetailContainer = (LinearLayout) findViewById(R.id.calendar_detail_container);
        findViewById(R.id.calendar_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarDetailBack.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_detail);
        assignViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setEntryperiod(String s_date, String s_system, String s_format) {
        final View v = getLayoutInflater().inflate(R.layout.detail_entryperiod, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_entryperiod_date);
        final TextView system = (TextView) v.findViewById(R.id.detail_entryperiod_system);
        final TextView format = (TextView) v.findViewById(R.id.detail_entryperiod_format);
        date.setText(s_date);
        system.setText(s_system);
        format.setText(s_format);
        calendarDetailContainer.addView(v);
    }

    private void setEntryseat(String s_start, String s_end, String s_system, String s_contains, boolean isContains) {
        final View v = getLayoutInflater().inflate(R.layout.detail_entryseat, null);
        final TextView start = (TextView) v.findViewById(R.id.detail_entryseat_start);
        final TextView end = (TextView) v.findViewById(R.id.detail_entryseat_end);
        final TextView system = (TextView) v.findViewById(R.id.detail_entryseat_system);
        final TextView contains = (TextView) v.findViewById(R.id.detail_entryseat_contains);
        final TableRow contains_row = (TableRow) v.findViewById(R.id.entryseat_contains_row);
        start.setText(s_start);
        end.setText(s_end);
        system.setText(s_system);
        contains.setText(s_contains);
        if (!isContains) contains_row.setVisibility(View.GONE);
        calendarDetailContainer.addView(v);
    }

    private void setGroupdiscussion(String s_date, String s_time, String s_place, String s_clothes, boolean isPlace) {
        final View v = getLayoutInflater().inflate(R.layout.detail_groupdiscussion, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_groupdiscussion_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_groupdiscussion_time);
        final TextView place = (TextView) v.findViewById(R.id.detail_groupdiscussion_place);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_groupdiscussion_clothes);
        final TableRow place_row = (TableRow) v.findViewById(R.id.group_place_row);
        date.setText(s_date);
        time.setText(s_time);
        place.setText(s_place);
        clothes.setText(s_clothes);
        if(!isPlace) place_row.setVisibility(View.GONE);
        calendarDetailContainer.addView(v);
    }

    private void setGuidance(String s_date, String s_time, String s_place, boolean isPlace) {
        final View v = getLayoutInflater().inflate(R.layout.detail_guidance, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_guidance_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_guidance_time);
        final TextView place = (TextView) v.findViewById(R.id.detail_guidance_place);
        final TableRow place_row = (TableRow) v.findViewById(R.id.guidance_place_row);
        date.setText(s_date);
        time.setText(s_time);
        place.setText(s_place);
        if(!isPlace) place_row.setVisibility(View.GONE);
        calendarDetailContainer.addView(v);
    }

    private void setInterview(String s_title, String s_date, String s_time, String s_format, String s_person, String s_clothes, String s_place, boolean isPlace, boolean isPerson) {
        final View v = getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView) v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView) v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView) v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView) v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_interview_clothes);
        final TextView place = (TextView) v.findViewById(R.id.detail_interview_place);
        final TableRow place_row = (TableRow) v.findViewById(R.id.interview_place_row);
        final TableRow person_row = (TableRow) v.findViewById(R.id.interview_person_row);
        title.setText(s_title);
        date.setText(s_date);
        time.setText(s_time);
        format.setText(s_format);
        person.setText(s_person);
        clothes.setText(s_clothes);
        place.setText(s_place);
        if(!isPlace) place_row.setVisibility(View.GONE);
        if(!isPerson) person_row.setVisibility(View.GONE);
        calendarDetailContainer.addView(v);
    }

    private void setFinalinterview(String s_title, String s_date, String s_time, String s_format, String s_person, String s_clothes, String s_place, boolean isPlace, boolean isPerson) {
        final View v = getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView) v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView) v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView) v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView) v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_interview_clothes);
        final TextView place = (TextView) v.findViewById(R.id.detail_interview_place);
        final TableRow place_row = (TableRow) v.findViewById(R.id.interview_place_row);
        final TableRow person_row = (TableRow) v.findViewById(R.id.interview_person_row);
        title.setText(s_title);
        date.setText(s_date);
        time.setText(s_time);
        format.setText(s_format);
        person.setText(s_person);
        clothes.setText(s_clothes);
        place.setText(s_place);
        if(!isPlace) place_row.setVisibility(View.GONE);
        if(!isPerson) person_row.setVisibility(View.GONE);
        calendarDetailContainer.addView(v);
    }

    private void setPersonalseat(String s_start, String s_end, String s_system, String s_format) {
        final View v = getLayoutInflater().inflate(R.layout.detail_personalseat, null);
        final TextView start = (TextView) v.findViewById(R.id.detail_personalseat_start);
        final TextView end = (TextView) v.findViewById(R.id.detail_personalseat_end);
        final TextView system = (TextView) v.findViewById(R.id.detail_personalseat_system);
        final TextView format = (TextView) v.findViewById(R.id.detail_personalseat_format);
        start.setText(s_start);
        end.setText(s_end);
        system.setText(s_system);
        format.setText(s_format);
        calendarDetailContainer.addView(v);
    }

}
