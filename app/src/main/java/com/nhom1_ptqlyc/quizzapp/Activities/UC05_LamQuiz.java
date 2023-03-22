package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nhom1_ptqlyc.quizzapp.Adapters.LamQuizAdapter;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc05LamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.CauHoi;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;

import java.util.ArrayList;

public class UC05_LamQuiz extends DrawerBaseActivity {
    ActivityUc05LamQuizBinding binding;
    Quiz quiz;

    ArrayList<CauHoi> listCauHoi;
    LamQuizAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUc05LamQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");

        quiz=(Quiz) getIntent().getSerializableExtra("KEY_QUIZ");
        Log.d("Kiểm tra quiz nhận",quiz.getTen());

        listCauHoi= new ArrayList<>();
        listCauHoi=quiz.getListCauHoi();

        adapter= new LamQuizAdapter(getApplicationContext(),R.layout.row_hien_thi_cau_hoi,listCauHoi);
        binding.listViewCauHoi.setAdapter(adapter);


        binding.buttonHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nopBai();
            }
        });


    }
    void nopBai(){ArrayList<Integer> listCauTraLoiCuaNgDung = adapter.getListCauTraLoiCuaNgDung();
        Log.d("Ktra cautraloi", listCauTraLoiCuaNgDung.toString());}


}