package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc05LamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;

public class UC05_LamQuiz extends DrawerBaseActivity {
    ActivityUc05LamQuizBinding binding;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUc05LamQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");

        quiz=(Quiz) getIntent().getSerializableExtra("KEY_QUIZ");
        Log.d("Kiểm tra quiz nhận",quiz.getTen());


    }

}