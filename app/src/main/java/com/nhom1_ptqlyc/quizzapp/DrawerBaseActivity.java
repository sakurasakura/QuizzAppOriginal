package com.nhom1_ptqlyc.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nhom1_ptqlyc.quizzapp.Activities.UC02_DangNhap;
import com.nhom1_ptqlyc.quizzapp.Activities.UC08_XemTrangCaNhan;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;

    @Override
    public void setContentView(View view) {
        mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = mDrawerLayout.findViewById(R.id.ActivityContainer);
        container.addView(view);
        super.setContentView(mDrawerLayout);

        Toolbar toolbar = mDrawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = mDrawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.trangChu:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                break;
            case R.id.trangCaNhan:
                Intent intent1= new Intent(getApplicationContext(), UC08_XemTrangCaNhan.class);
                startActivity(intent1);
                break;
            case R.id.dangXuat:
                SharedPreferences sharedPreferences= getSharedPreferences("user_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                Intent intent2 = new Intent(getApplicationContext(), UC02_DangNhap.class);
                startActivity(intent2);
                Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void allocateActivityName(String name) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name + "");
        }
    }
}