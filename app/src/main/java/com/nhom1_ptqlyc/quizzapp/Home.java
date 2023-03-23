package com.nhom1_ptqlyc.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nhom1_ptqlyc.quizzapp.databinding.ActivityHomeBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc07XemBinhLuanBinding;

public class Home extends DrawerBaseActivity {
ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TRANG CHỦ");
    }
}