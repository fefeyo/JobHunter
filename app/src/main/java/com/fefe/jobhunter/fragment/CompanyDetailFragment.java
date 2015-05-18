package com.fefe.jobhunter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.Data;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyDetailFragment extends Fragment {
    private TextView detailTitle;
    private LinearLayout layout;
    private Data data;

    private boolean isEntryperiod, isEntryseat, isGroupdiscussion, isGuidance, isPersonalseat;
    private boolean isInterview_one, isInterview_twe, isInterview_three, isInterview_four, isInterview_five, isFinalinterview;

    private int interview_count;

    private void assignViews(View v) {
        detailTitle = (TextView) v.findViewById(R.id.detail_title);
        layout = (LinearLayout) v.findViewById(R.id.detail_container);
    }

    private void resetFlags(){
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
    }

    private void setFlags(){
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
    }

    public CompanyDetailFragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_company_detail, container, false);
        assignViews(v);
        resetFlags();
        final List<Data> dataList = new Select().from(Data.class).where("name = ? and time = ?", getArguments().getString("name"),getArguments().getString("time")).execute();
        data = dataList.get(0);
        detailTitle.setText(data.name);
        setFlags();
        return v;
    }

    public void onResume(){
        super.onResume();
        resetFlags();
        interview_count = 1;
    }

    private void setEntryperiod(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_entryperiod, null);
        final TextView date = (TextView)v.findViewById(R.id.detail_entryperiod_date);
        final TextView system = (TextView)v.findViewById(R.id.detail_entryperiod_system);
        final TextView format = (TextView)v.findViewById(R.id.detail_entryperiod_format);
        date.setText(data.entryperiod_month+"月"+data.entryperiod_day+"日");
        system.setText(data.entryperiod_system);
        format.setText(data.entryperiod_format);
        layout.addView(v);
    }

    private void setEntryseat(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_entryseat, null);
        final TextView start = (TextView)v.findViewById(R.id.detail_entryseat_start);
        final TextView end = (TextView)v.findViewById(R.id.detail_entryseat_end);
        final TextView system = (TextView)v.findViewById(R.id.detail_entryseat_system);
        final TextView contains = (TextView)v.findViewById(R.id.detail_entryseat_contains);
        start.setText(data.entryseat_start_month+"月"+data.entryseat_start_day+"日から");
        end.setText(data.entryseat_end_month+"月"+data.entryseat_end_day+"日まで");
        system.setText(data.entryseat_system);
        contains.setText(data.entryseat_contains);
        layout.addView(v);
    }

    private void setGroupdiscussion(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_groupdiscussion, null);
        final TextView date = (TextView)v.findViewById(R.id.detail_groupdiscussion_date);
        final TextView time = (TextView)v.findViewById(R.id.detail_groupdiscussion_time);
        final TextView place = (TextView)v.findViewById(R.id.detail_groupdiscussion_place);
        final TextView clothes = (TextView)v.findViewById(R.id.detail_groupdiscussion_clothes);
        date.setText(data.groupdiscussion_month+"月"+data.groupdiscussion_day+"日");
        time.setText(data.groupdiscussion_time);
        place.setText(data.groupdiscussion_place);
        clothes.setText(data.groupdiscussion_clothes);
        layout.addView(v);
    }

    private void setGuidance(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_guidance, null);
        final TextView date = (TextView)v.findViewById(R.id.detail_guidance_date);
        final TextView time = (TextView)v.findViewById(R.id.detail_guidance_time);
        final TextView place = (TextView)v.findViewById(R.id.detail_guidance_place);
        date.setText(data.guidance_month+"月"+data.guidance_day+"日");
        time.setText(data.guidance_time);
        place.setText(data.guidance_place);
        layout.addView(v);
    }

    private void setInterview(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView)v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView)v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView)v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView)v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView)v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView)v.findViewById(R.id.detail_interview_clothes);
        title.setText(interview_count+"次面接 ");
        layout.addView(v);
    }

    private void setFinalinterview(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_interview, null);
        final TextView title = (TextView)v.findViewById(R.id.detail_interview_title);
        final TextView date = (TextView)v.findViewById(R.id.detail_interview_date);
        final TextView time = (TextView)v.findViewById(R.id.detail_interview_time);
        final TextView format = (TextView)v.findViewById(R.id.detail_interview_format);
        final TextView person = (TextView)v.findViewById(R.id.detail_interview_person);
        final TextView clothes = (TextView)v.findViewById(R.id.detail_interview_clothes);
        title.setText("最終面接");
        date.setText(data.interview_month_final+"月"+data.interview_day_final+"日");
        time.setText(data.interview_time_final);
        format.setText(data.interview_format_final);
        /*　TODO:チェックボックスなので見せ方を考える　*/
        person.setText("");
        clothes.setText(data.interview_clothes_final);
        layout.addView(v);
    }

    private void setPersonalseat(){
        final View v = getActivity().getLayoutInflater().inflate(R.layout.detail_personalseat, null);
        final TextView start = (TextView)v.findViewById(R.id.detail_personalseat_start);
        final TextView end = (TextView)v.findViewById(R.id.detail_personalseat_end);
        final TextView system = (TextView)v.findViewById(R.id.detail_personalseat_system);
        final TextView format = (TextView)v.findViewById(R.id.detail_personalseat_format);
        start.setText(data.personal_start_month+"月"+data.personal_start_day+"日から");
        end.setText(data.personal_end_month+"月"+data.personal_end_day+"日まで");
        system.setText(data.personalseat_system);
        format.setText(data.personalseat_format);
        layout.addView(v);

    }

}
