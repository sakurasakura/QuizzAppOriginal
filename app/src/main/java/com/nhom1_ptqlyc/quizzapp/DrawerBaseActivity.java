package com.nhom1_ptqlyc.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;

    @Override
    public void setContentView(View view) {
        mDrawerLayout=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = mDrawerLayout.findViewById(R.id.ActivityContainer);
        container.addView(view);
        super.setContentView(mDrawerLayout);

        Toolbar toolbar= mDrawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = mDrawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public void allocateActivityName(String name){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(name+"");
        }
    }
}