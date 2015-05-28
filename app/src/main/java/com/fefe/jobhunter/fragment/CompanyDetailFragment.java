package com.fefe.jobhunter.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * グループディスカッション場所
 * 面接相手＆場所（最終も）
 */
public class CompanyDetailFragment extends Fragment {
    private TextView detailTitle;
    private LinearLayout layout;
    private Data data;

    private HashMap<String, Calendar> calendarList;
    private String[] keylist;
    private ScrollView back;

    private boolean isEntryperiod, isEntryseat, isGroupdiscussion, isGuidance, isPersonalseat;
    private boolean isInterview_one, isInterview_twe, isInterview_three, isInterview_four, isInterview_five, isFinalinterview;

    private int interview_count;

    private void assignViews(View v) {
        detailTitle = (TextView) v.findViewById(R.id.detail_title);
        layout = (LinearLayout) v.findViewById(R.id.detail_container);
        calendarList = new HashMap<>();
        back = (ScrollView)v.findViewById(R.id.detail_back);
        v.findViewById(R.id.detail_scrolll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    private void resetFlags() {
        isEntryperiod = false;
        isEntryseat = false;
        isGroupdiscussion = false;
        isGuidance = false;
        isPersonalseat = false;
        isInterview_one = false;
        isInterview_twe = false;
        isInterview_three = false;
        isInterview_four = false;
        isInterview_five = false;
        isFinalinterview = false;
        interview_count = 1;
    }

    private void setPage() {
        isEntryperiod = data.used_entryperiod;
        isEntryseat = data.used_entryseat;
        isGroupdiscussion = data.used_groupdiscussion;
        isGuidance = data.used_guidance;
        isPersonalseat = data.used_personalseat;
        isInterview_one = data.used_interview_one;
        isInterview_twe = data.used_interview_twe;
        isInterview_three = data.used_interview_three;
        isInterview_four = data.used_interview_four;
        isInterview_five = data.used_interview_five;
        isFinalinterview = data.used_interview_final;
        interview_count = 1;
        setCalendarList();
    }

    public CompanyDetailFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_company_detail, container, false);
        assignViews(v);
        resetFlags();
        final List<Data> dataList = new Select().from(Data.class).where("name = ? and time = ?", getArguments().getString("name"), getArguments().getString("time")).execute();
        data = dataList.get(0);
        detailTitle.setText(data.name);
        back.setBackgroundColor(getResources().getColor(Integer.parseInt(data.color)));
        setPage();
        return v;
    }

    public void onResume() {
        super.onResume();
        resetFlags();
    }

    private void setEntryperiod() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_entryperiod, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_entryperiod_date);
        final TextView system = (TextView) v.findViewById(R.id.detail_entryperiod_system);
        final TextView format = (TextView) v.findViewById(R.id.detail_entryperiod_format);
        date.setText(data.entryperiod_month + "月" + data.entryperiod_day + "日");
        system.setText(data.entryperiod_system);
        format.setText(data.entryperiod_format);
        layout.addView(v);
    }

    private void setEntryseat() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_entryseat, null);
        final TextView start = (TextView) v.findViewById(R.id.detail_entryseat_start);
        final TextView end = (TextView) v.findViewById(R.id.detail_entryseat_end);
        final TextView system = (TextView) v.findViewById(R.id.detail_entryseat_system);
        final TextView contains = (TextView) v.findViewById(R.id.detail_entryseat_contains);
        final TableRow contains_row = (TableRow)v.findViewById(R.id.entryseat_contains_row);
        start.setText(data.entryseat_start_month + "月" + data.entryseat_start_day + "日から");
        end.setText(data.entryseat_end_month + "月" + data.entryseat_end_day + "日まで");
        system.setText(data.entryseat_system);
        contains.setText(data.entryseat_contains);
        if(data.entryseat_contains.equals("")) contains_row.setVisibility(View.GONE);
        layout.addView(v);
    }

    private void setGroupdiscussion() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_groupdiscussion, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_groupdiscussion_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_groupdiscussion_time);
        final TextView place = (TextView) v.findViewById(R.id.detail_groupdiscussion_place);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_groupdiscussion_clothes);
        final TableRow place_row = (TableRow)v.findViewById(R.id.group_place_row);
        date.setText(data.groupdiscussion_month + "月" + data.groupdiscussion_day + "日");
        time.setText(data.groupdiscussion_time);
        place.setText(data.groupdiscussion_place);
        clothes.setText(data.groupdiscussion_clothes);
        if(data.groupdiscussion_place.equals("")) place_row.setVisibility(View.GONE);
        layout.addView(v);
    }

    private void setGuidance() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_guidance, null);
        final TextView date = (TextView) v.findViewById(R.id.detail_guidance_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_guidance_time);
        final TextView place = (TextView) v.findViewById(R.id.detail_guidance_place);
        final TableRow place_row = (TableRow)v.findViewById(R.id.guidance_place_row);
        date.setText(data.guidance_month + "月" + data.guidance_day + "日");
        time.setText(data.guidance_time);
        place.setText(data.guidance_place);
        if(data.guidance_place.equals("")) place_row.setVisibility(View.GONE);
        layout.addView(v);
    }

    private void setInterview() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView) v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView) v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView) v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView) v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_interview_clothes);
        final TextView place = (TextView)v.findViewById(R.id.detail_interview_place);
        final TableRow place_row = (TableRow)v.findViewById(R.id.interview_place_row);
        final TableRow person_row = (TableRow)v.findViewById(R.id.interview_person_row);
        title.setText(interview_count + "次面接 ");
        setInterviewItem(date, time, format, person, clothes, place, place_row, person_row);
        interview_count++;
        layout.addView(v);
    }

    private void setFinalinterview() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView) v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView) v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView) v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView) v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView) v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView) v.findViewById(R.id.detail_interview_clothes);
        final TextView place = (TextView)v.findViewById(R.id.detail_interview_place);
        final TableRow place_row = (TableRow)v.findViewById(R.id.interview_place_row);
        final TableRow person_row = (TableRow)v.findViewById(R.id.interview_person_row);
        title.setText("最終面接");
        date.setText(data.interview_month_final + "月" + data.interview_day_final + "日");
        time.setText(data.interview_time_final);
        format.setText(data.interview_format_final);
        String interview_person = "";
        if (data.interview_person_student_final) interview_person += "・学生";
        if (data.interview_person_hr_final) interview_person += "・人事";
        if (data.interview_person_ceo_final) interview_person += "・CEO";
        if (data.interview_person_cto_final) interview_person += "・CTO";
        person.setText(interview_person);
        clothes.setText(data.interview_clothes_final);
        place.setText(data.interview_place_final);
        if(data.interview_place_final.equals("")) place_row.setVisibility(View.GONE);
        if(!data.interview_person_ceo_final&&!data.interview_person_cto_final&&!data.interview_person_hr_final&&!data.interview_person_student_final) person_row.setVisibility(View.GONE);
        layout.addView(v);
    }

    private void setPersonalseat() {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_personalseat, null);
        final TextView start = (TextView) v.findViewById(R.id.detail_personalseat_start);
        final TextView end = (TextView) v.findViewById(R.id.detail_personalseat_end);
        final TextView system = (TextView) v.findViewById(R.id.detail_personalseat_system);
        final TextView format = (TextView) v.findViewById(R.id.detail_personalseat_format);
        start.setText(data.personal_start_month + "月" + data.personal_start_day + "日から");
        end.setText(data.personal_end_month + "月" + data.personal_end_day + "日まで");
        system.setText(data.personalseat_system);
        format.setText(data.personalseat_format);
        layout.addView(v);
    }

    private void setInterviewItem(final TextView date, final TextView time, final TextView format, final TextView person, final TextView clothes,
                                  final TextView place, final TableRow place_row, final TableRow person_row) {
        switch (interview_count) {
            case 1:
                date.setText(data.interview_month_one + "月" + data.interview_day_one + "日");
                time.setText(data.interview_time_one);
                format.setText(data.interview_format_one);
                String interview_person_one = "";
                if (data.interview_person_student_one) interview_person_one += "・学生";
                if (data.interview_person_hr_one) interview_person_one += "・人事";
                if (data.interview_person_ceo_one) interview_person_one += "・CEO";
                if (data.interview_person_cto_one) interview_person_one += "・CTO";
                person.setText(interview_person_one);
                clothes.setText(data.interview_clothes_one);
                place.setText(data.interview_place_one);
                if(data.interview_place_one.equals("")) place_row.setVisibility(View.GONE);
                if(!data.interview_person_ceo_one&&!data.interview_person_cto_one&&!data.interview_person_hr_one&&!data.interview_person_student_one) person_row.setVisibility(View.GONE);
                break;
            case 2:
                date.setText(data.interview_month_twe + "月" + data.interview_day_twe + "日");
                time.setText(data.interview_time_twe);
                format.setText(data.interview_format_twe);
                String interview_person_twe = "";
                if (data.interview_person_student_twe) interview_person_twe += "・学生";
                if (data.interview_person_hr_twe) interview_person_twe += "・人事";
                if (data.interview_person_ceo_twe) interview_person_twe += "・CEO";
                if (data.interview_person_cto_twe) interview_person_twe += "・CTO";
                person.setText(interview_person_twe);
                clothes.setText(data.interview_clothes_twe);
                place.setText(data.interview_place_twe);
                if(data.interview_place_twe.equals("")) place_row.setVisibility(View.GONE);
                if(!data.interview_person_ceo_twe&&!data.interview_person_cto_twe&&!data.interview_person_hr_twe&&!data.interview_person_student_twe) person_row.setVisibility(View.GONE);
                break;
            case 3:
                date.setText(data.interview_month_three + "月" + data.interview_day_three + "日");
                time.setText(data.interview_time_three);
                format.setText(data.interview_format_three);
                String interview_person_three = "";
                if (data.interview_person_student_three) interview_person_three += "・学生";
                if (data.interview_person_hr_three) interview_person_three += "・人事";
                if (data.interview_person_ceo_three) interview_person_three += "・CEO";
                if (data.interview_person_cto_three) interview_person_three += "・CTO";
                person.setText(interview_person_three);
                clothes.setText(data.interview_clothes_three);
                place.setText(data.interview_place_three);
                if(data.interview_place_three.equals("")) place_row.setVisibility(View.GONE);
                if(!data.interview_person_ceo_three&&!data.interview_person_cto_three&&!data.interview_person_hr_three&&!data.interview_person_student_three) person_row.setVisibility(View.GONE);
                break;
            case 4:
                date.setText(data.interview_month_four + "月" + data.interview_day_four + "日");
                time.setText(data.interview_time_four);
                format.setText(data.interview_format_four);
                String interview_person_four = "";
                if (data.interview_person_student_four) interview_person_four += "・学生";
                if (data.interview_person_hr_four) interview_person_four += "・人事";
                if (data.interview_person_ceo_four) interview_person_four += "・CEO";
                if (data.interview_person_cto_twe) interview_person_four += "・CTO";
                person.setText(interview_person_four);
                clothes.setText(data.interview_clothes_four);
                place.setText(data.interview_place_four);
                if(data.interview_place_four.equals("")) place_row.setVisibility(View.GONE);
                if(!data.interview_person_ceo_four&&!data.interview_person_cto_four&&!data.interview_person_hr_four&&!data.interview_person_student_four) person_row.setVisibility(View.GONE);
                break;
            case 5:
                date.setText(data.interview_month_five + "月" + data.interview_day_five + "日");
                time.setText(data.interview_time_five);
                format.setText(data.interview_format_five);
                String interview_person_five = "";
                if (data.interview_person_student_five) interview_person_five += "・学生";
                if (data.interview_person_hr_five) interview_person_five += "・人事";
                if (data.interview_person_ceo_five) interview_person_five += "・CEO";
                if (data.interview_person_cto_five) interview_person_five += "・CTO";
                person.setText(interview_person_five);
                clothes.setText(data.interview_clothes_five);
                place.setText(data.interview_place_five);
                if(data.interview_place_five.equals("")) place_row.setVisibility(View.GONE);
                if(!data.interview_person_ceo_five&&!data.interview_person_cto_five&&!data.interview_person_hr_five&&!data.interview_person_student_five) person_row.setVisibility(View.GONE);
                break;
        }
    }

    private void setCalendar(String name, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        calendarList.put(name, c);
    }

    private void setCalendarList() {
        if (isEntryperiod)
            setCalendar("エントリー締め切り", data.entryperiod_month, data.entryperiod_day);
        if (isEntryseat)
            setCalendar("エントリーシート", data.entryseat_end_month, data.entryseat_end_day);
        if (isGroupdiscussion)
            setCalendar("グループディスカッション", data.groupdiscussion_month, data.groupdiscussion_day);
        if (isGuidance)
            setCalendar("説明会", data.guidance_month, data.guidance_day);
        if (isPersonalseat)
            setCalendar("履歴書", data.personal_end_month, data.personal_end_day);
        if (isInterview_one)
            setCalendar("1次面接", data.interview_month_one, data.interview_day_one);
        if (isInterview_twe)
            setCalendar("2次面接", data.interview_month_twe, data.interview_day_twe);
        if (isInterview_three)
            setCalendar("3次面接", data.interview_month_three, data.interview_day_three);
        if (isInterview_four)
            setCalendar("4次面接", data.interview_month_four, data.interview_day_four);
        if (isInterview_five)
            setCalendar("5次面接", data.interview_month_five, data.interview_day_five);
        if (isFinalinterview)
            setCalendar("最終面接", data.interview_month_final, data.interview_day_final);
        sort();
        setViews();
    }

    private void sort() {
        final Set<String> keyset = calendarList.keySet();
        if (!keyset.isEmpty()) {
            keylist = keyset.toArray(new String[]{});
        }
        if (null != keylist) {
            for (int i = 0; calendarList.size() - 1 > i; i++) {
                for (int n = calendarList.size() - 1; n > i; n--) {
                    int check = calendarList.get(keylist[n]).compareTo(calendarList.get(keylist[n - 1]));
                    if (check == -1) {
                        String tmp = keylist[n];
                        keylist[n] = keylist[n - 1];
                        keylist[n - 1] = tmp;
                    }
                }
            }
        }
    }

    private void setViews() {
        if (null != keylist) {
            for (String s : keylist) {
                switch (s) {
                    case "エントリー締め切り":
                        setEntryperiod();
                        break;
                    case "エントリーシート":
                        setEntryseat();
                        break;
                    case "グループディスカッション":
                        setGroupdiscussion();
                        break;
                    case "説明会":
                        setGuidance();
                        break;
                    case "履歴書":
                        setPersonalseat();
                        break;
                    case "1次面接":
                        setInterview();
                        break;
                    case "2次面接":
                        setInterview();
                        break;
                    case "3次面接":
                        setInterview();
                        break;
                    case "4次面接":
                            setInterview();
                        break;
                    case "5次面接":
                        setFinalinterview();
                        break;
                    case "最終面接":
                        setInterview();
                        break;
                }
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}
