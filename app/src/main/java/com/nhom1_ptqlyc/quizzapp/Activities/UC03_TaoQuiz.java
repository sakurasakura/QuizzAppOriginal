package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc03TaoQuizBinding;

public class UC03_TaoQuiz extends DrawerBaseActivity {
    ActivityUc03TaoQuizBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUc03TaoQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TẠO QUIZ");
    }

}