package com.fefe.jobhunter.adapter;

import android.content.Context;
import android.graphics.Color;
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
public class MySpinnerAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    public static String[] color = {
//            "ラベルカラーを選んでください",
            "#E60012",
            "#EB6100",
            "#F39800",
            "#FCC800",
            "#FFF100",
            "#CFDB00",
            "#8FC31F",
            "#22AC38",
            "#009944",
            "#009B6B",
            "#009E96",
            "#00A0C1",
            "#00A0E9",
            "#0086D1",
            "#0068B7",
            "#00479D",
            "#1D2088",
            "#601986",
            "#920783",
            "#BE0081",
            "#E4007F",
            "#E5006A",
            "#E5004F",
            "#E60033",
    };

    public MySpinnerAdapter(Context c){
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if(convertView == null){
            convertView = inflater.inflate(R.layout.spinner_item_row, null);
        }
        CheckedTextView text = (CheckedTextView) convertView.findViewById(R.id.color_row);
            text.setBackgroundColor(Color.parseColor(color[position]));
        return convertView;
    }
}