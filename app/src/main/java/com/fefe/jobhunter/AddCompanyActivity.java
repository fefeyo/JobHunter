package com.fefe.jobhunter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.fefe.jobhunter.adapter.MySpinnerAdapter;
import com.fefe.jobhunter.fragment.AddCompanyListFragment;
import com.fefe.jobhunter.item.Data;
import com.fefe.jobhunter.item.InterviewItem;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


/*
　
[現在のエラー状況]
・実機で見るとViewが真っ黒になり何も見えない　未解決
・色選択しているのにNullが入る　解決　理由：ヘッダーをつけていたため
・選考を追加して完了すると落ちる(エントリー締め切りが怪しい)　解決　理由：ラジオボタンのNull
　*/

public class AddCompanyActivity extends ActionBarActivity {
    private LinearLayout layout;
    private int yearId;
    public HashMap<Integer, String> map;
    public int interview_count;

    private Spinner colorSpinner;
    private String labelColor;

    private ScrollView scroller;

    //　使われたかどうか
    public boolean
            isGuidance,
            isEntrySeat,
            isPersonalSeat,
            isGroupDiscussion,
            isInterview,
            isEntryPeriod,
            isFinalInterview;
    public int count;

    /*　社名、部署名　*/
    private BootstrapEditText company_name;
    private BootstrapEditText company_place;

    /*　説明会　*/
    private BootstrapEditText guidance_place;
    private DatePicker guidance_date;
    private TimePicker guidance_time;

    /*　エントリーシート　*/
    private DatePicker entryseat_start;
    private DatePicker entryseat_end;
    private RadioGroup entryseat_system;
    private BootstrapEditText entryseat_contains;

    /*　履歴書　*/
    private DatePicker personalseat_start;
    private DatePicker personalseat_end;
    private RadioGroup personalseat_system;
    private RadioGroup personalseat_format;

    /*　グループディスカッション　*/
    private BootstrapEditText groupdiscussion_place;
    private RadioGroup groupdiscussion_clothes;
    private DatePicker groupdiscussion_date;
    private TimePicker groupdiscussion_time;

    /*　面接　*/
    private BootstrapEditText interview_place;
    private HashMap<Integer, InterviewItem> interview_item;

    /*　最終面接　*/
    private BootstrapEditText finalInterview_place;
    private RadioGroup finalInterview_clothes;
    private DatePicker finalInterview_date;
    private TimePicker finalInterview_time;
    private RadioGroup finalInterview_format;
    private CheckBox finalInterview_student;
    private CheckBox finalInterview_cto;
    private CheckBox finalInterview_ceo;
    private CheckBox finalInterview_hr;

    /*　エントリー締め切り　*/
    private RadioGroup entryperiod_system;
    private RadioGroup entryperiod_format;
    private DatePicker entryperiod_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        getSupportActionBar().setTitle("選考を追加");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layout = (LinearLayout) findViewById(R.id.add_container);
        company_name = (BootstrapEditText) findViewById(R.id.company_name);
        company_place = (BootstrapEditText) findViewById(R.id.company_place);
        colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        colorSpinner.setAdapter(new MySpinnerAdapter(getApplicationContext()));
        yearId = Resources.getSystem().getIdentifier("year", "id", "android");
        initMap();
        interview_item = new HashMap<>();
        scroller = (ScrollView) findViewById(R.id.scroller);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCompanyListFragment fragment = new AddCompanyListFragment();
                fragment.show(getSupportFragmentManager(), "予定追加");
            }
        });

        findViewById(R.id.scrollup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroller.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner s = (Spinner) parent;
                labelColor = (String) s.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void onResume() {
        super.onResume();
        count = 7;
        interview_count = 1;
        setBool();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_company, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_save) {
            endOfEdit();
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setGuidance() {
        final View v = getLayoutInflater().inflate(R.layout.guidance, null);
        v.findViewById(R.id.guidance_date).findViewById(yearId).setVisibility(View.GONE);
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        guidance_place = (BootstrapEditText) v.findViewById(R.id.guidance_place);
        guidance_date = (DatePicker) v.findViewById(R.id.guidance_date);
        guidance_time = (TimePicker) v.findViewById(R.id.guidance_time);
        isGuidance = true;
        v.findViewById(R.id.guidance_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 0, view);
            }
        });
    }

    public void setEntrySeat() {
        final View v = getLayoutInflater().inflate(R.layout.entry_seat, null);
        v.findViewById(R.id.entry_seat_start).findViewById(yearId).setVisibility(View.GONE);
        v.findViewById(R.id.entry_seat_end).findViewById(yearId).setVisibility(View.GONE);
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        entryseat_start = (DatePicker) v.findViewById(R.id.entry_seat_start);
        entryseat_end = (DatePicker) v.findViewById(R.id.entry_seat_end);
        entryseat_system = (RadioGroup) v.findViewById(R.id.entry_seat_system);
        entryseat_contains = (BootstrapEditText) v.findViewById(R.id.entry_seat_contains);
        isEntrySeat = true;
        v.findViewById(R.id.entryseat_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 1, view);
            }
        });
    }

    public void setPersonalSeat() {
        final View v = getLayoutInflater().inflate(R.layout.personal_seat, null);
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        personalseat_start = (DatePicker) v.findViewById(R.id.personal_seat_start);
        personalseat_start.findViewById(yearId).setVisibility(View.GONE);
        personalseat_end = (DatePicker) v.findViewById(R.id.personal_seat_end);
        personalseat_end.findViewById(yearId).setVisibility(View.GONE);
        personalseat_system = (RadioGroup) v.findViewById(R.id.personal_seat_system);
        personalseat_format = (RadioGroup) v.findViewById(R.id.personal_seat_format);
        isPersonalSeat = true;
        v.findViewById(R.id.personal_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 2, view);
            }
        });
    }

    public void setGroupDiscussion() {
        final View v = getLayoutInflater().inflate(R.layout.group_discussion, null);
        v.findViewById(R.id.group_discussion_date).findViewById(yearId).setVisibility(View.GONE);
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        groupdiscussion_place = (BootstrapEditText) v.findViewById(R.id.group_discussion_place);
        groupdiscussion_clothes = (RadioGroup) v.findViewById(R.id.group_discussion_clothes);
        groupdiscussion_date = (DatePicker) v.findViewById(R.id.group_discussion_date);
        groupdiscussion_time = (TimePicker) v.findViewById(R.id.group_discussion_time);
        isGroupDiscussion = true;
        v.findViewById(R.id.groupdiscussion_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 3, view);
            }
        });
    }

    public void setInterview(String count) {
        final View v = getLayoutInflater().inflate(R.layout.interview, null);
        v.findViewById(R.id.interview_date).findViewById(yearId).setVisibility(View.GONE);
        TextView interview_name = (TextView) v.findViewById(R.id.interview_count);
        interview_name.setText(count + "面接");
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);

        interview_place = (BootstrapEditText) v.findViewById(R.id.interview_place);
        RadioGroup interview_clothes = (RadioGroup) v.findViewById(R.id.interview_clothes);
        DatePicker interview_date = (DatePicker) v.findViewById(R.id.interview_date);
        TimePicker interview_time = (TimePicker)v.findViewById(R.id.interview_time);
        RadioGroup interview_format = (RadioGroup) v.findViewById(R.id.interview_format);
        CheckBox interview_student = (CheckBox) v.findViewById(R.id.interview_person_student);
        CheckBox interview_cto = (CheckBox) v.findViewById(R.id.interview_person_cto);
        CheckBox interview_ceo = (CheckBox) v.findViewById(R.id.interview_person_ceo);
        CheckBox interview_hr = (CheckBox) v.findViewById(R.id.interview_person_hr);
        InterviewItem item = new InterviewItem();
        item.setInterview_place(interview_place);
        item.setInterview_clothes(interview_clothes);
        item.setInterview_date(interview_date);
        item.setInterview_time(interview_time);
        item.setInterview_format(interview_format);
        item.setInterview_student(interview_student);
        item.setInterview_cto(interview_cto);
        item.setInterview_ceo(interview_ceo);
        item.setInterview_hr(interview_hr);
        interview_item.put(interview_count, item);
        isInterview = true;
        v.findViewById(R.id.interview_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 4, view);
            }
        });
    }

    public void setFinalInterview() {
        final View v = getLayoutInflater().inflate(R.layout.interview, null);
        v.findViewById(R.id.interview_date).findViewById(yearId).setVisibility(View.GONE);
        TextView interview_count = (TextView) v.findViewById(R.id.interview_count);
        interview_count.setText("最終面接");
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        finalInterview_place = (BootstrapEditText) v.findViewById(R.id.interview_place);
        finalInterview_clothes = (RadioGroup) v.findViewById(R.id.interview_clothes);
        finalInterview_date = (DatePicker) v.findViewById(R.id.interview_date);
        finalInterview_time = (TimePicker)v.findViewById(R.id.interview_time);
        finalInterview_format = (RadioGroup) v.findViewById(R.id.interview_format);
        finalInterview_student = (CheckBox) v.findViewById(R.id.interview_person_student);
        finalInterview_cto = (CheckBox) v.findViewById(R.id.interview_person_cto);
        finalInterview_ceo = (CheckBox) v.findViewById(R.id.interview_person_ceo);
        finalInterview_hr = (CheckBox) v.findViewById(R.id.interview_person_hr);
        isFinalInterview = true;
        v.findViewById(R.id.interview_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 5, view);
            }
        });
    }

    public void setEntryPeriod() {
        final View v = getLayoutInflater().inflate(R.layout.entry_period, null);
        v.findViewById(R.id.entry_period_date).findViewById(yearId).setVisibility(View.GONE);
        v.setAnimation(getAnimation(0, 1, 500));
        layout.addView(v);
        entryperiod_system = (RadioGroup) v.findViewById(R.id.entry_period_system);
        entryperiod_format = (RadioGroup) v.findViewById(R.id.entry_period_format);
        entryperiod_date = (DatePicker) v.findViewById(R.id.entry_period_date);
        isEntryPeriod = true;
        v.findViewById(R.id.entryperiod_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeView(v, 6, view);
            }
        });
    }

    private void initMap() {
        map = new HashMap<>();
        map.put(0, "説明会");
        map.put(1, "エントリーシート");
        map.put(2, "履歴書");
        map.put(3, "グループディスカッション");
        map.put(4, "面接");
        map.put(5, "最終面接");
        map.put(6, "エントリー締め切り");
    }

    private void endOfEdit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("完了");
        builder.setMessage("編集を完了してよろしいですか？");
        builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveToSQL();
            }
        });
        builder.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public void saveToSQL() {
        boolean flag = false;
        if (setError()) return;
        Data data = new Data();
        if (!checkCompanyName(company_name.getText().toString())) {
            data.name = company_name.getText().toString();
        } else {
            return;
        }
        data.position = company_place.getText().toString();
        data.time = getToday();
        if (isGuidance) {
            data.guidance_place = guidance_place.getText().toString();
            data.guidance_month = getMonth(guidance_date);
            data.guidance_day = getDay(guidance_date);
            data.guidance_time = getTime(guidance_time);
            data.used_guidance = true;
        }
        if (isEntrySeat) {
            data.entryseat_start_month = getMonth(entryseat_start);
            data.entryseat_start_day = getDay(entryseat_start);
            data.entryseat_end_month = getMonth(entryseat_end);
            data.entryseat_end_day = getDay(entryseat_end);
            if (!checkRadio(entryseat_system)) {
                data.entryseat_system = getRadio(entryseat_system);
            } else {
                radioAlert("エントリーシート");
                flag = true;
            }
            data.entryseat_contains = entryseat_contains.getText().toString();
            data.used_entryseat = true;
        }
        if (isPersonalSeat) {
            data.personal_start_month = getMonth(personalseat_start);
            data.personal_start_day = getDay(personalseat_start);
            data.personal_end_month = getMonth(personalseat_end);
            data.personal_end_day = getDay(personalseat_end);
            if (!checkRadio(personalseat_system) && !checkRadio(personalseat_format)) {
                data.personalseat_system = getRadio(personalseat_system);
                data.personalseat_format = getRadio(personalseat_format);
            } else {
                radioAlert("履歴書");
                flag = true;
            }
            data.used_personalseat = true;
        }
        if (isGroupDiscussion) {
            data.groupdiscussion_place = groupdiscussion_place.getText().toString();
            if (!checkRadio(groupdiscussion_clothes)) {
                data.groupdiscussion_clothes = getRadio(groupdiscussion_clothes);
            } else {
                radioAlert("グループディスカッション");
                flag = true;
            }
            data.groupdiscussion_month = getMonth(groupdiscussion_date);
            data.groupdiscussion_day = getDay(groupdiscussion_date);
            data.groupdiscussion_time = getTime(groupdiscussion_time);
            data.used_groupdiscussion = true;
        }
        if (isInterview) {
            for (int i = 1; interview_item.size() + 1 > i; i++) {
                InterviewItem item = interview_item.get(i);
                if (i == 1) {
                    data.interview_place_one = item.getInterview_place().getText().toString();
                    data.interview_month_one = getMonth(item.getInterview_date());
                    data.interview_day_one = getDay(item.getInterview_date());
                    data.interview_time_one = getTime(item.getInterview_time());
                    if (!checkRadio(item.getInterview_clothes()) && !checkRadio(item.getInterview_format())) {
                        data.interview_clothes_one = getRadio(item.getInterview_clothes());
                        data.interview_format_one = getRadio(item.getInterview_format());
                    } else {
                        radioAlert(i + "次面接");
                        flag = true;
                    }
                    data.interview_person_student_one = item.getInterview_student().isChecked();
                    data.interview_person_cto_one = item.getInterview_cto().isChecked();
                    data.interview_person_ceo_one = item.getInterview_ceo().isChecked();
                    data.interview_person_hr_one = item.getInterview_hr().isChecked();
                    data.used_interview_one = true;
                } else if (i == 2) {
                    data.interview_place_twe = item.getInterview_place().getText().toString();
                    data.interview_month_twe = getMonth(item.getInterview_date());
                    data.interview_day_twe = getDay(item.getInterview_date());
                    data.interview_time_twe = getTime(item.getInterview_time());
                    if (!checkRadio(item.getInterview_clothes()) && !checkRadio(item.getInterview_format())) {
                        data.interview_clothes_twe = getRadio(item.getInterview_clothes());
                        data.interview_format_twe = getRadio(item.getInterview_format());
                    } else {
                        radioAlert(i + "次面接");
                        flag = true;
                    }
                    data.interview_person_student_twe = item.getInterview_student().isChecked();
                    data.interview_person_cto_twe = item.getInterview_cto().isChecked();
                    data.interview_person_ceo_twe = item.getInterview_ceo().isChecked();
                    data.interview_person_hr_twe = item.getInterview_hr().isChecked();
                    data.used_interview_twe = true;
                } else if (i == 3) {
                    data.interview_place_three = item.getInterview_place().getText().toString();
                    data.interview_month_three = getMonth(item.getInterview_date());
                    data.interview_day_three = getDay(item.getInterview_date());
                    data.interview_time_three = getTime(item.getInterview_time());
                    if (!checkRadio(item.getInterview_clothes()) && !checkRadio(item.getInterview_format())) {
                        data.interview_clothes_three = getRadio(item.getInterview_clothes());
                        data.interview_format_three = getRadio(item.getInterview_format());
                    } else {
                        radioAlert(i + "次面接");
                        flag = true;
                    }
                    data.interview_person_student_three = item.getInterview_student().isChecked();
                    data.interview_person_cto_three = item.getInterview_cto().isChecked();
                    data.interview_person_ceo_three = item.getInterview_ceo().isChecked();
                    data.interview_person_hr_three = item.getInterview_hr().isChecked();
                    data.used_interview_three = true;
                } else if (i == 4) {
                    data.interview_place_four = item.getInterview_place().getText().toString();
                    data.interview_month_four = getMonth(item.getInterview_date());
                    data.interview_day_four = getDay(item.getInterview_date());
                    data.interview_time_four = getTime(item.getInterview_time());
                    if (!checkRadio(item.getInterview_clothes()) && !checkRadio(item.getInterview_format())) {
                        data.interview_clothes_four = getRadio(item.getInterview_clothes());
                        data.interview_format_four = getRadio(item.getInterview_format());
                    } else {
                        radioAlert(i + "次面接");
                        flag = true;
                    }
                    data.interview_person_student_four = item.getInterview_student().isChecked();
                    data.interview_person_cto_four = item.getInterview_cto().isChecked();
                    data.interview_person_ceo_four = item.getInterview_ceo().isChecked();
                    data.interview_person_hr_four = item.getInterview_hr().isChecked();
                    data.used_interview_four = true;
                } else if (i == 5) {
                    data.interview_place_five = item.getInterview_place().getText().toString();
                    data.interview_month_five = getMonth(item.getInterview_date());
                    data.interview_day_five = getDay(item.getInterview_date());
                    data.interview_time_five = getTime(item.getInterview_time());
                    if (!checkRadio(item.getInterview_clothes()) && !checkRadio(item.getInterview_format())) {
                        data.interview_clothes_five = getRadio(item.getInterview_clothes());
                        data.interview_format_five = getRadio(item.getInterview_format());
                    } else {
                        radioAlert(i + "次面接");
                        flag = true;
                    }
                    data.interview_person_student_five = item.getInterview_student().isChecked();
                    data.interview_person_cto_five = item.getInterview_cto().isChecked();
                    data.interview_person_ceo_five = item.getInterview_ceo().isChecked();
                    data.interview_person_hr_five = item.getInterview_hr().isChecked();
                    data.used_interview_five = true;
                }
            }
        }
        if (isFinalInterview) {
            data.interview_place_final = finalInterview_place.getText().toString();
            data.interview_month_final = getMonth(finalInterview_date);
            data.interview_day_final = getDay(finalInterview_date);
            data.interview_time_final = getTime(finalInterview_time);
            if (!checkRadio(finalInterview_clothes) && !checkRadio(finalInterview_format)) {
                data.interview_clothes_final = getRadio(finalInterview_clothes);
                data.interview_format_final = getRadio(finalInterview_format);
            } else {
                radioAlert("最終面接");
                flag = true;
            }
            data.interview_person_student_final = finalInterview_student.isChecked();
            data.interview_person_cto_final = finalInterview_cto.isChecked();
            data.interview_person_ceo_final = finalInterview_ceo.isChecked();
            data.interview_person_hr_final = finalInterview_hr.isChecked();
            data.used_interview_final = true;
        }
        if (isEntryPeriod) {
            if (!checkRadio(entryperiod_system) && !checkRadio(entryperiod_format)) {
                data.entryperiod_system = getRadio(entryperiod_system);
                data.entryperiod_format = getRadio(entryperiod_format);
            } else {
                radioAlert("エントリー締め切り");
                flag = true;
            }
            data.entryperiod_month = getMonth(entryperiod_date);
            data.entryperiod_day = getDay(entryperiod_date);
            data.used_entryperiod = true;
        }
        data.color = labelColor;
        if(!flag) {
            data.save();
            finish();
        }
    }


    private boolean setError() {
        int flag = 0;
        if (company_name.length() == 0) {
            company_name.setError("会社名を入力してください");
            flag++;
        } else {
            company_name.setError(null);
        }
        if (isGuidance) {
            if (checkDate(guidance_date)) {
                Toast.makeText(this, "説明会の日程を確認して下さい", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (isEntrySeat) {
            if (checkDate(entryseat_start) || checkDate(entryseat_end)) {
                Toast.makeText(this, "エントリーシートの日程を確認して下さい", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (isPersonalSeat) {
            if (checkDate(personalseat_start) || checkDate(personalseat_end)) {
                Toast.makeText(this, "履歴書の日程を確認して下さい", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (isGroupDiscussion) {
            if (checkDate(groupdiscussion_date)) {
                Toast.makeText(this, "グループディスカッションの日程を確認して下さい", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (isInterview) {

        }
        if (isFinalInterview) {
            if (checkDate(finalInterview_date)) {
                Toast.makeText(this, "最終面接の日程を確認して下さい", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (isEntryPeriod) {
            if (checkDate(entryperiod_date)) {
                Toast.makeText(this, "エントリー締め切りの日程を確認してください", Toast.LENGTH_SHORT).show();
                flag++;
            }
        }
        if (flag > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDate(DatePicker picker) {
        Calendar c = Calendar.getInstance();
        int pMonth = picker.getMonth();
        int pDay = picker.getDayOfMonth();
        int nMonth = c.get(Calendar.MONTH);
        int nDay = c.get(Calendar.DATE);
        if (pMonth == nMonth && pDay == nDay) {
            return true;
        } else {
            return false;
        }
    }

    private void setBool() {
        isGuidance = false;
        isEntrySeat = false;
        isPersonalSeat = false;
        isGroupDiscussion = false;
        isInterview = false;
        isEntryPeriod = false;
        isFinalInterview = false;
    }

    private String getRadio(RadioGroup radio) {
        final RadioButton button = (RadioButton) radio.findViewById(radio.getCheckedRadioButtonId());
        return button.getText().toString();
    }

    private int getMonth(DatePicker picker) {
        return picker.getMonth() + 1;
    }

    private int getDay(DatePicker picker) {
        return picker.getDayOfMonth();
    }

    private void closeView(final View rootView, final int position, final View clickView) {
        AlphaAnimation animation = getAnimation(1, 0, 300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                clickView.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.removeView(rootView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        rootView.startAnimation(animation);
        count++;
        switch (position) {
            case 0:
                map.put(0, "説明会");
                isGuidance = false;
                break;
            case 1:
                map.put(1, "エントリーシート");
                isEntrySeat = false;
                break;
            case 2:
                map.put(2, "履歴書");
                isPersonalSeat = false;
                break;
            case 3:
                map.put(3, "グループディスカッション");
                isGroupDiscussion = false;
                break;
            case 4:
                map.put(4, "面接");
                interview_count--;
                count--;
                break;
            case 5:
                map.put(5, "最終面接");
                isFinalInterview = false;
                break;
            case 6:
                map.put(6, "エントリー締め切り");
                isEntryPeriod = false;
                break;
        }
    }

    private AlphaAnimation getAnimation(int start, int end, int duration) {
        final AlphaAnimation animation = new AlphaAnimation(start, end);
        animation.setDuration(duration);
        return animation;
    }

    private String getToday() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        return month + "月" + day + "日";
    }

    private boolean checkCompanyName(String name) {
        boolean isExist = false;
        List<Data> data = new Select().from(Data.class).execute();
        for (Data item : data) {
            if (name.equals(item.name)) {
                isExist = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("重複");
                builder.setMessage("同じ名前の会社名が登録されています。\n確認して下さい。");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                break;
            }
        }
        return isExist;
    }

    private boolean checkRadio(RadioGroup r) {
        if (r.getCheckedRadioButtonId() == -1) {
            return true;
        }
        return false;
    }

    private void radioAlert(String name) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認");
        builder.setMessage(name + "の入力項目を確認して下さい");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private String getTime(TimePicker picker){
        int hour = picker.getCurrentHour();
        int minute = picker.getCurrentMinute();
        if(hour == 0 && minute == 0){
            return "";
        }
        return hour+"："+minute;
    }

}