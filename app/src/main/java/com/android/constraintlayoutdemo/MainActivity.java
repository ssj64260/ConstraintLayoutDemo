package com.android.constraintlayoutdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;
    private BottomNavigationView mNavigation;
    private BottomNavigationBar mBottomNavigationBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mTextMessage = findViewById(R.id.message);
        mNavigation = findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setTabSelectedListener(mTabSelect);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_home))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_24dp, R.string.title_dashboard))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_notifications))
//                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Movies & TV"))
//                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "Games"))
                .initialise();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    PokemonDetailActivity.show(MainActivity.this);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private BottomNavigationBar.OnTabSelectedListener mTabSelect = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            switch (position) {
                case 0:
                    mNavigation.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    mNavigation.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    mNavigation.setSelectedItemId(R.id.navigation_notifications);
                    break;
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };
}
