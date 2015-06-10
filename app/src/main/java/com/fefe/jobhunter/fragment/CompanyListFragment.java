package com.fefe.jobhunter.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.activeandroid.query.Select;
import com.fefe.jobhunter.MainActivity;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.adapter.CompanyListAdapter;
import com.fefe.jobhunter.item.CompanyListItem;
import com.fefe.jobhunter.item.Data;
import com.fefe.jobhunter.swipemenulistview.SwipeMenu;
import com.fefe.jobhunter.swipemenulistview.SwipeMenuCreator;
import com.fefe.jobhunter.swipemenulistview.SwipeMenuItem;
import com.fefe.jobhunter.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
* [現在のエラー状況]
*リストのEmptyが表示されない
*戻ってきた時にリストが表示されない
* 
* */

public class CompanyListFragment extends Fragment implements AdapterView.OnItemClickListener, SwipeMenuListView.OnMenuItemClickListener{
    @InjectView(R.id.todo_list)
    SwipeMenuListView list;

    private ArrayList<CompanyListItem> arr;
    private CompanyListItem item;
    private HashMap<Integer, String> companies;


    public CompanyListFragment() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company_list, container, false);
        ButterKnife.inject(this, v);

        createMenu(list);
        final LinearLayout empty = (LinearLayout)v.findViewById(R.id.empty_view);
        list.setEmptyView(empty);
        list.setOnItemClickListener(this);
        list.setOnMenuItemClickListener(this);
        setArray();
        return v;
    }

    public void onResume(){
        super.onResume();
        setHasOptionsMenu(true);
        MainActivity.setTitle("会社一覧");
        if(list != null){
            setArray();
        }
    }

    private void setArray(){
        arr = new ArrayList<>();
        companies = new HashMap<>();
        int counter = 0;
        final List<Data> data = new Select().from(Data.class).execute();
        for(Data d : data){
            item = new CompanyListItem();
            item.setName(d.name);
            item.setPosition(d.position);
            item.setColor(d.color);
            item.setDate(d.time);
            arr.add(item);
            companies.put(counter, d.name);
            counter++;
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

    private void createMenu(final SwipeMenuListView list){
        final SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                final SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(120);
                deleteItem.setTitle("削除");
                deleteItem.setTitleSize(20);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        list.setMenuCreator(creator);
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index){
            case 0:
                final String companyName = companies.get(position);
                deleteCompany(companyName);
                return true;
        }
        return false;
    }

    private void deleteCompany(final String company){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("削除");
        builder.setMessage(company + "を削除してよろしいですか？");
        builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final List<Data> datas = new Select().from(Data.class).where("name = ?", company).execute();
                final Data deleteData = datas.get(0);
                deleteData.delete();
                setArray();
            }
        });
        builder.setNegativeButton("いいえ", null);
        builder.create().show();
    }

}
