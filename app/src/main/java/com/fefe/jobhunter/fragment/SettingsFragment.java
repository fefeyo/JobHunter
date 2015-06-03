package com.fefe.jobhunter.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.fefe.jobhunter.MainActivity;
import com.fefe.jobhunter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Switch mSwitch;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    public SettingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mSwitch = (Switch) v.findViewById(R.id.save_calendar);
        mSwitch.setOnCheckedChangeListener(this);
        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        setHasOptionsMenu(true);
        MainActivity.setTitle("設定");
        setChecked();
    }

    private void setChecked(){
        sp = getActivity().getSharedPreferences("jobhunter", Context.MODE_PRIVATE);
        if(sp.getBoolean("save", false)){
            mSwitch.setChecked(true);
        }else{
            mSwitch.setChecked(false);
        }
    }

    private void isSwitch(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        editor = sp.edit();
        editor.putBoolean("save", isChecked);
        editor.apply();
    }
}
