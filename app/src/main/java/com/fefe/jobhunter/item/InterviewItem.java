package com.fefe.jobhunter.item;

import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.beardedhen.androidbootstrap.BootstrapEditText;

/**
 * Created by USER on 2015/05/13.
 */
public class InterviewItem {
    private BootstrapEditText interview_place;
    private RadioGroup interview_clothes;
    private DatePicker interview_date;
    private TimePicker interview_time;
    private RadioGroup interview_format;
    private CheckBox interview_student;
    private CheckBox interview_cto;
    private CheckBox interview_ceo;
    private CheckBox interview_hr;

    public InterviewItem(){

    }

    public BootstrapEditText getInterview_place(){
        return this.interview_place;
    }
    public void setInterview_place(BootstrapEditText interview_place){
        this.interview_place = interview_place;
    }

    public RadioGroup getInterview_clothes(){
        return this.interview_clothes;
    }

    public void setInterview_clothes(RadioGroup interview_clothes){
        this.interview_clothes = interview_clothes;
    }

    public DatePicker getInterview_date(){
        return this.interview_date;
    }

    public void setInterview_date(DatePicker interview_date){
        this.interview_date = interview_date;
    }

    public RadioGroup getInterview_format(){
        return this.interview_format;
    }

    public void setInterview_format(RadioGroup interview_format){
        this.interview_format = interview_format;
    }

    public CheckBox getInterview_student(){
        return interview_student;
    }

    public void setInterview_student(CheckBox interview_student){
        this.interview_student = interview_student;
    }

    public CheckBox getInterview_cto(){
        return this.interview_cto;
    }

    public void setInterview_cto(CheckBox interview_cto){
        this.interview_cto = interview_cto;
    }

    public CheckBox getInterview_ceo(){
        return this.interview_ceo;
    }

    public void setInterview_ceo(CheckBox interview_ceo){
        this.interview_ceo = interview_ceo;
    }

    public CheckBox getInterview_hr(){
        return this.interview_hr;
    }

    public void setInterview_hr(CheckBox interview_hr){
        this.interview_hr = interview_hr;
    }

    public void setInterview_time(TimePicker interview_time) {
        this.interview_time = interview_time;
    }

    public TimePicker getInterview_time() {
        return interview_time;
    }
}
