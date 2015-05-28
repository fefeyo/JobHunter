package com.fefe.jobhunter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.CompanyListItem;

import java.util.ArrayList;

/**
 * Created by USER on 2015/05/13.
 */
public class CompanyListAdapter extends ArrayAdapter<CompanyListItem>{
    private LayoutInflater inflater;

    public CompanyListAdapter(Context context, int resource, ArrayList<CompanyListItem> arr) {
        super(context, resource, arr);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(R.layout.company_list_row, null);
        }
        CompanyListItem item = getItem(position);
        TextView name = (TextView)convertView.findViewById(R.id.list_companyname);
        name.setText(item.getName());
        TextView c_position = (TextView)convertView.findViewById(R.id.list_companyposition);
        c_position.setText(item.getPosition());
        TextView time = (TextView)convertView.findViewById(R.id.list_companytime);
        time.setText("登録日："+item.getDate());
        FrameLayout layout = (FrameLayout)convertView.findViewById(R.id.company_list_container);

        /*　ToDo:Color.parseColorでNullが発生　*/
        layout.setBackgroundColor(getContext().getResources().getColor(Integer.parseInt(item.getColor())));
        return convertView;
    }
}
