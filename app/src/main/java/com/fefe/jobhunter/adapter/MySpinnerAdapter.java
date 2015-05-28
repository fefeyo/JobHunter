package com.fefe.jobhunter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.fefe.jobhunter.R;

/**
 * Created by USER on 2015/05/13.
 */
public class MySpinnerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public static int[] color = {
//            "ラベルカラーを選んでください",
            R.color.one,
            R.color.twe,
            R.color.three,
            R.color.four,
            R.color.five,
            R.color.six,
            R.color.seven,
            R.color.eight,
            R.color.nine,
            R.color.ten,
            R.color.eleven,
            R.color.twenteen,
            R.color.thirteen,
            R.color.fourteen,
            R.color.fifteen,
            R.color.eighteen,
            R.color.nineteen,
            R.color.twenty,
            R.color.twentyone,
            R.color.twentytwe,
            R.color.twentythree,
            R.color.twentyfour,
            R.color.twentyfive,
    };
    private Context context;

    public MySpinnerAdapter(Context c) {
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
    }

    @Override
    public int getCount() {
        return color.length;
    }

    @Override
    public Object getItem(int position) {
        return color[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item_row, null);
        }
        CheckedTextView text = (CheckedTextView) convertView.findViewById(R.id.color_row);
        text.setBackgroundColor(context.getResources().getColor(color[position]));
        return convertView;
    }
}