package com.fefe.jobhunter;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fefe.jobhunter.fragment.CalendarFragment;
import com.fefe.jobhunter.fragment.CompanyListFragment;
import com.fefe.jobhunter.fragment.InfomationFragment;
import com.fefe.jobhunter.fragment.SettingsFragment;
import com.fefe.jobhunter.fragment.WeekEventFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    @InjectView(R.id.drawer_list)
    ListView mList;
    @InjectView(R.id.main_drawer)
    DrawerLayout drawer;

    private static int scene;
    private static ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle("会社一覧");
        mActionBar.setIcon(getResources().getDrawable(R.drawable.ic_drawer));
        ArrayList<String> arr = new ArrayList<>();
        arr.add("会社一覧");
        arr.add("選考を追加");
//        arr.add("カレンダー");
        arr.add("今週の選考");
        arr.add("サポート");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.simple_list_item,
                arr
        );
        mList.setOnItemClickListener(this);
        mList.setAdapter(adapter);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, new CompanyListFragment());
        ft.commit();
        scene = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.fragment_container, new CompanyListFragment());
//        ft.commit();
//        scene = 0;
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
                if (scene != 0) {
                    ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                    ft.replace(R.id.fragment_container, new CompanyListFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    scene = 0;
                }
                drawer.closeDrawers();
                break;
            case 1:
                i = new Intent(MainActivity.this, AddCompanyActivity.class);
                drawer.closeDrawers();
                break;

            /**
             * カレンダーは同じアクティビティに飛ばして表示位置を変更させる
             * カレンダー実装予定
             */
//            case 2:
//                if (scene != 2) {
//                    ft.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_top);
//                    ft.replace(R.id.fragment_container, new CalendarFragment());
//                    ft.addToBackStack(null);
//                    ft.commit();
//                    scene = 2;
//                }
//                drawer.closeDrawers();
//                break;

            case 2:
                if (scene != 2) {
                    ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                    ft.replace(R.id.fragment_container, new WeekEventFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    scene = 2;
                }
                drawer.closeDrawers();
                break;
            /*　Googleアカウント、カレンダーへの挿入許可など　*/
            case 3:
                if (scene != 3) {
                    ft.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom);
                    ft.replace(R.id.fragment_container, new InfomationFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    scene = 3;
                }
                drawer.closeDrawers();
                break;
        }
        if (i != null) {
            startActivity(i);
        }
    }

    public static void setTitle(String title) {
        mActionBar.setTitle(title);
    }

}
