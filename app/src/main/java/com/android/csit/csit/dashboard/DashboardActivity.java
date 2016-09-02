package com.android.csit.csit.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.csit.csit.MainFragment;
import com.android.csit.csit.MyFilesFragment;
import com.android.csit.csit.NoteFragment;
import com.android.csit.csit.R;

public class DashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ListView listView;
    private static final String FILENAME = "csit_prefs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.lv_menu);

        setToolbar();
        setDrawerToggle();
        setUpList();
        setUpInitialFragment();
    }

    private void setUpInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_sub_effects, new NoteFragment()).commit();
    }

    private void setUpList() {
        listView.setAdapter(new MenuListAdapter(this));

        getHeader(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_sub_effects, new NoteFragment()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_sub_effects, new MainFragment()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_sub_effects, new MyFilesFragment()).commit();
                        break;
                }
                drawerLayout.closeDrawers();
            }
        });
    }

    private void getHeader(ListView listView) {
        View view = LayoutInflater.from(this).inflate(R.layout.head_view, null, false);
        TextView tvUserName = (TextView) view.findViewById(R.id.tv_username);

        tvUserName.setText(getSavedUserName());

        listView.addHeaderView(view);
    }

    /**
     * initialize {@link Toolbar}
     * set toolbar elevation and title
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setElevation(8);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    /**
     * initialize drawer toggle and add drawer toggle listener to drawer layout
     */
    private void setDrawerToggle() {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.app_name, R.string.app_name);

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }

    private String getSavedPassword() {
        SharedPreferences prefs = getSharedPreferences(FILENAME, MODE_PRIVATE);
        return prefs.getString("password", "NA");
    }

    private String getSavedUserName() {
        SharedPreferences prefs = getSharedPreferences(FILENAME, MODE_PRIVATE);
        return prefs.getString("username", "NA");
    }

}
