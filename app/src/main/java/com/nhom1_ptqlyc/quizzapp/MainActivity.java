package com.nhom1_ptqlyc.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.nhom1_ptqlyc.quizzapp.Activities.UC02_DangNhap;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = new File(getApplicationContext().getApplicationInfo().dataDir + "/shared_prefs/" + "user_data" + ".xml");
        boolean isPreExist= f.exists();

        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        if (!preferences.contains("username") || !isPreExist){
            Intent intent = new Intent(getApplicationContext(), UC02_DangNhap.class);
            startActivity(intent);
        }else {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
        }
    }
}