package com.nhom1_ptqlyc.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nhom1_ptqlyc.quizzapp.Activities.UC03_TaoQuiz;

public class WorkflowUser extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workflow_user);

        replaceFragment(new HomeUserFragment());

        mDrawerLayout= findViewById(R.id.drawerLayout_User);



        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId()){
                    case R.id.trangCaNhan:
                        replaceFragment(new TrangCaNhanFragment());
                        break;
                    case R.id.trangChu:
                        replaceFragment(new HomeUserFragment());
                        break;
                    case R.id.dangXuat:
                        replaceFragment(new DangXuatFragment());
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Bạn đang ở trang chủ.", Toast.LENGTH_SHORT).show();
    }

    void replaceFragment(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.workflowUser_frame,f);
        fragmentTransaction.commit();
    }
}