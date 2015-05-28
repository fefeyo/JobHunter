package com.fefe.jobhunter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.WeekeventItem;

import java.util.ArrayList;

/**
 * Created by USER on 2015/05/21.
 */
public class WeekeventAdapter extends ArrayAdapter<WeekeventItem>{
    private LayoutInflater inflater;

    public WeekeventAdapter(Context context, int resource, ArrayList<WeekeventItem> arr) {
        super(context, resource, arr);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(R.layout.weekevent_row, null);
        }
        final WeekeventItem item = getItem(position);
        final TableRow place_row = (TableRow)convertView.findViewById(R.id.place_row);
        final TableRow time_row = (TableRow)convertView.findViewById(R.id.time_row);
        final TextView company = (TextView)convertView.findViewById(R.id.weekevent_company);
        company.setText(item.getName());
        final TextView remainder = (TextView)convertView.findViewById(R.id.weekevent_remainder);
        remainder.setText(item.getRemainder());
        final TextView contains = (TextView)convertView.findViewById(R.id.weekevent_contains);
        contains.setText(item.getContains());
        final TextView date = (TextView)convertView.findViewById(R.id.weekevent_date);
        date.setText(item.getDate());
        final TextView time = (TextView)convertView.findViewById(R.id.weekevent_time);
        time.setText(item.getTime());
        if(item.getTime().equals("")) time_row.setVisibility(View.GONE);
        final TextView place = (TextView)convertView.findViewById(R.id.weekevent_place);
        place.setText(item.getPlace());
        if(item.getPlace().equals("")) place_row.setVisibility(View.GONE);

        return convertView;
    }

}
