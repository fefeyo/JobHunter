package com.fefe.jobhunter.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.fefe.jobhunter.MainActivity;
import com.fefe.jobhunter.R;
import com.fefe.jobhunter.item.InfoItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfomationFragment extends Fragment {
    @InjectView(R.id.infoList)
    private ListView mList;

    private MyInfoAdapter adapter;
    private InfoItem item;

    private Switch s;

    public InfomationFragment() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_infomation, container, false);
        ButterKnife.inject(this, v);
        final ArrayList<InfoItem> arr = new ArrayList<>();
        insertItem(arr);
        adapter = new MyInfoAdapter(
                getActivity().getApplicationContext(),
                0,
                arr
        );
        mList.setAdapter(adapter);
        return v;
    }

    @OnItemClick(R.id.infoList)
    void onItemClick(final ListView lv, int position){
        final InfoItem item = (InfoItem) lv.getItemAtPosition(position);
        if (item.getTitle().equals("お問い合わせ")) {
            final Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:info@sharetopics.com"));
            startActivity(intent);
        }
    }

    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        MainActivity.setTitle("サポート");
    }

    private class MyInfoAdapter extends ArrayAdapter<InfoItem> implements CompoundButton.OnCheckedChangeListener {
        private LayoutInflater inflater;

        public MyInfoAdapter(final Context context, final int resource, final ArrayList<InfoItem> arr) {
            super(context, 0, arr);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final InfoItem item = getItem(position);
            if (convertView == null && item.getTitle().equals("カレンダー同期")) {
                convertView = inflater.inflate(R.layout.info_calendar, null);
                final TextView text = (TextView) convertView.findViewById(R.id.info_calendar_title);
                text.setText(item.getTitle());
                s = (Switch) convertView.findViewById(R.id.info_switch);
                setChecked();
                s.setOnCheckedChangeListener(this);
            } else {
                convertView = inflater.inflate(R.layout.info_row, null);
                final TextView title = (TextView) convertView.findViewById(R.id.info_title);
                title.setText(item.getTitle());
                final TextView contains = (TextView) convertView.findViewById(R.id.info_contains);
                contains.setText(item.getContains());
            }
            return convertView;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            final SharedPreferences sp = getActivity().getSharedPreferences("jobhunter", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("save", isChecked);
            editor.apply();
        }

        private void setChecked() {
            final SharedPreferences sp = getActivity().getSharedPreferences("jobhunter", Context.MODE_PRIVATE);
            if (sp.getBoolean("save", false)) {
                s.setChecked(true);
            } else {
                s.setChecked(false);
            }
        }
    }

    private void insertItem(final ArrayList<InfoItem> arr) {
        item = new InfoItem();
        item.setTitle("カレンダー同期");
        item.setContains(">");
        arr.add(item);
        item = new InfoItem();
        item.setTitle("アプリ名");
        item.setContains("就活インサーター");
        arr.add(item);
        item = new InfoItem();
        item.setTitle("バージョン");
        item.setContains("1.0.0");
        arr.add(item);
        item = new InfoItem();
        item.setTitle("お問い合わせ");
        item.setContains("info@sharetopics.com");
        arr.add(item);
        item = new InfoItem();
        item.setTitle("コピーライト");
        item.setContains("©ArcheTypeNova");
        arr.add(item);
    }

}
