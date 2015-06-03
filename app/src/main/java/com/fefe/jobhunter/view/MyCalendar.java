package com.fefe.jobhunter.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fefe.jobhunter.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by USER on 2015/05/30.
 */
public class MyCalendar extends LinearLayout{
    @SuppressWarnings("unused")
    private static final String TAG = CalendarView.class.getSimpleName();

    private static final int WEEK_DAYS = 7;
    private static final int MAX_WEEK = 6;

    //週の始まりの曜日
    private static final int BIGINNING_DAY_OF_WEEK = Calendar.SUNDAY;
    //今日のフォント
    private static final int TODAY_COLOR = Color.RED;
    //今週の背景
    private static final int DEFAULT_COLOR = Color.DKGRAY;
    //通常のフォント
    private static final int TODAY_BACKGROUND_COLOR = Color.LTGRAY;
    //通常の背景
    private static final int DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT;

    //年月表示部分
    private TextView mTitileView;

    //週のレイアウト
    private LinearLayout mWeekLayout;
    private ArrayList<LinearLayout> mWeeks = new ArrayList<>();

    //コンストラクタ
    public MyCalendar(Context context){
        this(context, null);
    }

    /**
     * コンストラクタ
     * @param context
     * @param attrs
     */
    @SuppressWarnings("SimpleDateFormat")
    public MyCalendar(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setOrientation(VERTICAL);

        createTitleView(context);
        createWeekViews(context);
        createDayViews(context);
    }

    /**
     *　年月日表示用のタイトルを生成する
     * @param context
     */
    private void createTitleView(Context context){
        float scaleDensity = context.getResources().getDisplayMetrics().density;
        mTitileView = new TextView(context);
        mTitileView.setGravity(Gravity.CENTER_HORIZONTAL);
        mTitileView.setTextSize((int) scaleDensity * 14);
        mTitileView.setTypeface(null, Typeface.BOLD);
        mTitileView.setPadding(0, 0, 0, (int) (scaleDensity * 16));

        addView(mTitileView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }

    /**
     * 曜日表示用のレイアウト
     * @param context
     */
    private void createWeekViews(Context context){
        float scaleDensity = context.getResources().getDisplayMetrics().density;
        //　週表示レイアウト
        mWeekLayout = new LinearLayout(context);
        final Calendar calendar = Calendar.getInstance();
        //　週の頭をセット
        calendar.set(Calendar.DAY_OF_WEEK, BIGINNING_DAY_OF_WEEK);
        for(int i = 0;i < WEEK_DAYS;i++){
            final TextView text = new TextView(context);
            text.setGravity(Gravity.RIGHT);
            text.setPadding(0, 0, (int) (scaleDensity * 4), 0);
            LinearLayout.LayoutParams llp = new LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            llp.weight = 1;
            mWeekLayout.addView(text, llp);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        addView(mWeekLayout, new LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }


    private void createDayViews(Context context){
        float scaleDensity = context.getResources().getDisplayMetrics().density;
        //カレンダー部　最大6行必要
        for(int i = 0;i < MAX_WEEK;i++){
            final LinearLayout weekLine = new LinearLayout(context);
            mWeeks.add(weekLine);

            //1週間分の日付ビュー作成
            for(int j = 0;j < WEEK_DAYS;j++){
                final TextView dayView = new TextView(context);
                dayView.setGravity(Gravity.TOP | Gravity.RIGHT);
                dayView.setPadding(0, (int)(scaleDensity * 4), (int)(scaleDensity * 4), 0);
                final LayoutParams llp = new LayoutParams(
                        0,
                        (int)(scaleDensity * 48)
                );
                llp.weight = 1;
                weekLine.addView(dayView, llp);
            }
            this.addView(
                    weekLine,
                    new LinearLayoutCompat.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );
        }
    }

    public void set(int year, int month){
        setTitle(year, month);
        setWeeks();
        setDays(year, month);
    }

    @SuppressWarnings("SimpleDateFormat")
    private void setTitle(int year, int month){
        final Calendar targetCalendar = getTargetCalendar(year, month);
        //年月フォーマット文字列
        final String formatString = mTitileView.getContext().getString(R.string.month_name_format);
        final SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        mTitileView.setText(formatter.format(targetCalendar.getTime()));
    }

    /**
     * 曜日設定
     */
    @SuppressWarnings("SimpleDateFormat")
    private void setWeeks(){
        final Calendar week = Calendar.getInstance();
        week.set(
                Calendar.DAY_OF_WEEK,
                BIGINNING_DAY_OF_WEEK
                );
        //週の頭をセット
        final SimpleDateFormat weekFormatter = new SimpleDateFormat("E");
        for(int i = 0;i < WEEK_DAYS;i++){
            final TextView text = (TextView)mWeekLayout.getChildAt(i);
            //テキストに曜日をセット
            text.setText(weekFormatter.format(week.getTime()));
            week.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    /**
     * 日付を設定していくメソッド
     * @param year　年の指定
     * @param month　月の指定
     */
    private void setDays(int year, int month){
        final Calendar targetCalendar = getTargetCalendar(year, month);

        int skipCount = getSkipCount(targetCalendar);
        int lastDay = targetCalendar.getActualMaximum(Calendar.DATE);
        int dayCounter = 1;

        final Calendar todayCalendar = Calendar.getInstance();
        int todayYear = todayCalendar.get(Calendar.YEAR);
        int todayMonth = todayCalendar.get(Calendar.MONTH);
        int todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH);

        for(int i = 0;i < MAX_WEEK;i++){
            final LinearLayout weekLayout = mWeeks.get(i);
            weekLayout.setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
            for(int j = 0;j < WEEK_DAYS;j++){
                final TextView dayView = (TextView)weekLayout.getChildAt(j);
                //　第一週かつskipCountが残っていれば
                if(i == 0 && skipCount > 0){
                    dayView.setText(" ");
                    skipCount--;
                    continue;
                }

                //最終日より大きければ
                if(lastDay < dayCounter){
                    dayView.setText(" ");
                    continue;
                }

                //　日付を設定
                dayView.setText(String.valueOf(dayCounter));

                final boolean isToday =
                        todayYear == year &&
                        todayMonth == month &&
                        todayDay == dayCounter;

                if(isToday){
                    //赤文字
                    dayView.setTextColor(TODAY_COLOR);
                    dayView.setTypeface(null, Typeface.BOLD);
                    //週の背景グレー
                    weekLayout.setBackgroundColor(TODAY_BACKGROUND_COLOR);
                }else{
                    dayView.setTextColor(DEFAULT_COLOR);
                    dayView.setTypeface(null, Typeface.NORMAL);
                }
                dayCounter++;
            }
        }

    }

    /**
     * カレンダーの最初の空白の個数を求める
     * @param targetCalendar　指定した月のCalendarのInstance
     * @return skipCount
     */
    private int getSkipCount(final Calendar targetCalendar){
        // 空白の個数
        int skipCount;
        //　1日の曜日
        int firstDayOfWeekOfMonth = targetCalendar.get(Calendar.DAY_OF_WEEK);
        if(BIGINNING_DAY_OF_WEEK > firstDayOfWeekOfMonth){
            skipCount = firstDayOfWeekOfMonth - BIGINNING_DAY_OF_WEEK + WEEK_DAYS;
        }else{
            skipCount = firstDayOfWeekOfMonth - BIGINNING_DAY_OF_WEEK;
        }
        return skipCount;
    }

    /**
     *
     * @param year
     * @param month
     * @return
     */
    private Calendar getTargetCalendar(int year, int month){
        final Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.clear();
        targetCalendar.set(Calendar.YEAR, year);
        targetCalendar.set(Calendar.MONTH, month);
        targetCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return targetCalendar;
    }
}
