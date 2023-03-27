package com.nhom1_ptqlyc.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.nhom1_ptqlyc.quizzapp.Activities.UC02_DangNhap;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = cm.getActiveNetwork()!=null;
        if (!connected){
            Toast.makeText(this, "Kết nối mạng có vấn đề!", Toast.LENGTH_LONG).show();
            return;
        }

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