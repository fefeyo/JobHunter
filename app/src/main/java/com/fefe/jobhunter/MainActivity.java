package com.fefe.jobhunter;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fefe.jobhunter.fragment.CalendarFragment;
import com.fefe.jobhunter.fragment.CompanyListFragment;
import com.fefe.jobhunter.fragment.SettingsFragment;
import com.fefe.jobhunter.fragment.WeekEventFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private ListView mList;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("会社一覧");
        mList = (ListView) findViewById(R.id.drawer_list);
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("選考を追加");
        arr.add("カレンダー");
        arr.add("今週の選考");
        arr.add("設定");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.simple_list_item,
                arr
        );
        mList.setOnItemClickListener(this);
//        View v = getLayoutInflater().inflate(R.layout.swipe_header,null);
//        mList.addHeaderView(v, null, false);
        mList.setAdapter(adapter);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, new CompanyListFragment());
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_add) {
            Intent i = new Intent(MainActivity.this, AddCompanyActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                i = new Intent(MainActivity.this, AddCompanyActivity.class);
                drawer.closeDrawers();
                break;
            /*　カレンダーは同じアクティビティに飛ばして表示位置を変更させる　*/
            case 1:
                ft.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_top);
                ft.replace(R.id.fragment_container, new CalendarFragment());
                ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawers();
                break;
            case 2:
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, new WeekEventFragment());
                ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawers();
                break;
            /*　Googleアカウント、カレンダーへの挿入許可など　*/
            case 3:
                ft.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom);
                ft.replace(R.id.fragment_container, new SettingsFragment());
                ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawers();
                break;
        }
        if (i != null) {
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed(){
        int count = getSupportFragmentManager().getBackStackEntryCount();
    }

}
