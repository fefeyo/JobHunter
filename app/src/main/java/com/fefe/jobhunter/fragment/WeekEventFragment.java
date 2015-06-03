package com.fefe.jobhunter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.MainActivity;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.adapter.WeekeventAdapter;
import com.fefe.jobhunter.item.Data;
import com.fefe.jobhunter.item.WeekeventItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekEventFragment extends Fragment {
    private Calendar now;
    private ArrayList<WeekeventItem> arr;
    private HashMap<Integer, WeekeventItem> map;

    public WeekEventFragment() {
    }

    /*
    * TODO:日付計算の確認
    * あと○日の調整
    * 8月にすると明らかにバグる
    * 解決済かも（未コンパイル）
    *
    * */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_week_event, container, false);
        final ListView list = (ListView) v.findViewById(R.id.weekevent_list);
        final LinearLayout empty = (LinearLayout)v.findViewById(R.id.week_empty);
        list.setEmptyView(empty);
        setWeekeventList();
        final WeekeventAdapter adapter = new WeekeventAdapter(
                getActivity().getApplicationContext(),
                0,
                getWeekItemList()
        );
        list.setAdapter(adapter);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setWeekeventList() {
        final List<Data> dataList = new Select().from(Data.class).execute();
        arr = new ArrayList<>();
        now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int year;
        int month;
        int day;
        for (Data data : dataList) {
            if (data.used_guidance) {
                month = data.guidance_month;
                day = data.guidance_day;
                final Calendar c2 = Calendar.getInstance();
                year = setYear(month);
                c2.set(year, month - 1, day);
                long until_guidance = untilInWeek(now.getTime(), c2.getTime()) - 1;
                if (until_guidance <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("説明会");
                    item.setRemainder("あと" + until_guidance + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.guidance_time);
                    item.setPlace(data.guidance_place);
                    item.setCalendar(c2);
                    arr.add(item);
                }
            }
            if (data.used_entryseat) {
                month = data.entryseat_end_month;
                day = data.entryseat_end_day;
                final Calendar c3 = Calendar.getInstance();
                year = setYear(month);
                c3.set(year, month - 1, day);
                long until_entryseat = untilInWeek(now.getTime(), c3.getTime()) - 1;
                if (until_entryseat <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("エントリーシート期限");
                    item.setRemainder("あと" + until_entryseat + "日");
                    item.setDate(month + "月" + day + "日まで");
                    item.setTime("");
                    item.setPlace("");
                    item.setCalendar(c3);
                    arr.add(item);
                }
            }

            if (data.used_personalseat) {
                month = data.personal_end_month;
                day = data.personal_end_day;
                final Calendar c4 = Calendar.getInstance();
                year = setYear(month);
                c4.set(year, month - 1, day);
                long until_personal = untilInWeek(now.getTime(), c4.getTime()) - 1;
                if (until_personal <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("履歴書期限");
                    item.setRemainder("あと" + until_personal + "日");
                    item.setDate(month + "月" + day + "日まで");
                    item.setTime("");
                    item.setPlace("");
                    item.setCalendar(c4);
                    arr.add(item);
                }
            }

            if (data.used_groupdiscussion) {
                month = data.groupdiscussion_month;
                day = data.groupdiscussion_day;
                final Calendar c5 = Calendar.getInstance();
                year = setYear(month);
                c5.set(year, month - 1, day);
                long until_group = untilInWeek(now.getTime(), c5.getTime()) - 1;
                if (until_group <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("グループディスカッション");
                    item.setRemainder("あと" + until_group + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.groupdiscussion_time);
                    item.setPlace(data.groupdiscussion_place);
                    item.setCalendar(c5);
                    arr.add(item);
                }
            }

            if (data.used_interview_one) {
                month = data.interview_month_one;
                day = data.interview_day_one;
                final Calendar c6 = Calendar.getInstance();
                year = setYear(month);
                c6.set(year, month - 1, day);
                long until_interview_one = untilInWeek(now.getTime(), c6.getTime()) - 1;
                if (until_interview_one <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("１次面接");
                    item.setRemainder("あと" + until_interview_one + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_one);
                    item.setPlace(data.interview_place_one);
                    item.setCalendar(c6);
                    arr.add(item);
                }
            }

            if (data.used_interview_twe) {
                month = data.interview_month_twe;
                day = data.interview_day_twe;
                final Calendar c7 = Calendar.getInstance();
                year = setYear(month);
                c7.set(year, month - 1, day);
                long until_interview_twe = untilInWeek(now.getTime(), c7.getTime()) - 1;
                if (until_interview_twe <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("２次面接");
                    item.setRemainder("あと" + until_interview_twe + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_twe);
                    item.setPlace(data.interview_place_twe);
                    item.setCalendar(c7);
                    arr.add(item);
                }
            }

            if (data.used_interview_three) {
                month = data.interview_month_three;
                day = data.interview_day_three;
                final Calendar c8 = Calendar.getInstance();
                year = setYear(month);
                c8.set(year, month - 1, day);
                long until_interview_three = untilInWeek(now.getTime(), c8.getTime()) - 1;
                if (until_interview_three <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("３次面接");
                    item.setRemainder("あと" + until_interview_three + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_three);
                    item.setPlace(data.interview_place_three);
                    item.setCalendar(c8);
                    arr.add(item);
                }
            }

            if (data.used_interview_four) {
                month = data.interview_month_four;
                day = data.interview_day_four;
                final Calendar c9 = Calendar.getInstance();
                year = setYear(month);
                c9.set(year, month - 1, day);
                long until_interview_four = untilInWeek(now.getTime(), c9.getTime()) - 1;
                if (until_interview_four <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("４次面接");
                    item.setRemainder("あと" + until_interview_four + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_four);
                    item.setPlace(data.interview_place_four);
                    item.setCalendar(c9);
                    arr.add(item);
                }
            }

            if (data.used_interview_five) {
                month = data.interview_month_five;
                day = data.interview_day_five;
                final Calendar c10 = Calendar.getInstance();
                year = setYear(month);
                c10.set(year, month - 1, day);
                long until_interview_five = untilInWeek(now.getTime(), c10.getTime()) - 1;
                if (until_interview_five <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("５次面接");
                    item.setRemainder("あと" + until_interview_five + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_five);
                    item.setPlace(data.interview_place_five);
                    item.setCalendar(c10);
                    arr.add(item);
                }
            }

            if (data.used_interview_final) {
                month = data.interview_month_final;
                day = data.interview_day_final;
                final Calendar c11 = Calendar.getInstance();
                year = setYear(month);
                c11.set(year, month - 1, day);
                long until_interview_final = untilInWeek(now.getTime(), c11.getTime()) - 1;
                if (until_interview_final <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("最終面接");
                    item.setRemainder("あと" + until_interview_final + "日");
                    item.setDate(month + "月" + day + "日");
                    item.setTime(data.interview_time_final);
                    item.setPlace(data.interview_place_final);
                    item.setCalendar(c11);
                    arr.add(item);
                }
            }

            if (data.used_entryperiod) {
                month = data.entryperiod_month;
                day = data.entryperiod_day;
                final Calendar c12 = Calendar.getInstance();
                year = setYear(month);
                c12.set(year, month - 1, day);
                long until_entryperiod = untilInWeek(now.getTime(), c12.getTime()) - 1;
                if (until_entryperiod <= 7) {
                    final WeekeventItem item = new WeekeventItem();
                    item.setName(data.name);
                    item.setContains("エントリー締め切り");
                    item.setRemainder("あと" + until_entryperiod + "日");
                    item.setDate(month + "月" + day + "日まで");
                    item.setTime("");
                    item.setPlace("");
                    item.setCalendar(c12);
                    arr.add(item);
                }
            }
        }
    }

    private long untilInWeek(final Date nowDate, final Date targetDate) {
        return Math.abs((nowDate.getTime() - targetDate.getTime()) / 1000 / 60 / 60 / 24);
    }

    private int setYear(final int targetMonth) {
        if (now.get(Calendar.MONTH) - targetMonth > 0) {
            return now.get(Calendar.YEAR) + 1;
        } else {
            return now.get(Calendar.YEAR);
        }
    }

    private ArrayList<WeekeventItem> getWeekItemList() {
        final WeekeventItem[] items = arr.toArray(new WeekeventItem[]{});
        for (int i = 0; items.length - 1 > i; i++) {
            for (int n = items.length - 1; n > i; n--) {
                if (items[n].getCalendar().compareTo(items[n - 1].getCalendar()) == -1) {
                    WeekeventItem tmp = items[n];
                    items[n] = items[n - 1];
                    items[n - 1] = tmp;
                }
            }
        }
        arr = new ArrayList<>();
        for (WeekeventItem item : items) {
            arr.add(item);
        }
        return arr;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        MainActivity.setTitle("今週の選考");
    }


}
