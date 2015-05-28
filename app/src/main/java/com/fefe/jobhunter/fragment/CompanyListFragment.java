package com.fefe.jobhunter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.adapter.CompanyListAdapter;
import com.fefe.jobhunter.item.CompanyListItem;
import com.fefe.jobhunter.item.Data;

import java.util.ArrayList;
import java.util.List;

/*
* [現在のエラー状況]
*リストのEmptyが表示されない
*戻ってきた時にリストが表示されない
* 
* */

public class CompanyListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView list;
    private ArrayList<CompanyListItem> arr;
    private CompanyListItem item;


    public CompanyListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company_list, container, false);
        list = (ListView)v.findViewById(R.id.todo_list);
        final LinearLayout empty = (LinearLayout)v.findViewById(R.id.empty_view);
        list.setEmptyView(empty);
        list.setOnItemClickListener(this);
        setArray();
        return v;
    }

    public void onResume(){
        super.onResume();
        if(list != null){
            setArray();
        }
    }

    private void setArray(){
        arr = new ArrayList<>();
        List<Data> data = new Select().from(Data.class).execute();
        for(Data d : data){
            item = new CompanyListItem();
            item.setName(d.name);
            item.setPosition(d.position);
            item.setColor(d.color);
            item.setDate(d.time);
            arr.add(item);
        }
        list.setAdapter(new CompanyListAdapter(
                getActivity().getApplicationContext(),
                0,
                arr
        ));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CompanyListItem data = (CompanyListItem)parent.getItemAtPosition(position);
        CompanyDetailFragment fragment = new CompanyDetailFragment();
        Bundle b = new Bundle();
        b.putString("name", data.getName());
        b.putString("time", data.getDate());
        fragment.setArguments(b);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

}
