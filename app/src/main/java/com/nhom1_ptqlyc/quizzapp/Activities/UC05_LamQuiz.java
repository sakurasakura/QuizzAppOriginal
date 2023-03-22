package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

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
        binding = ActivityUc05LamQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");

        quiz = (Quiz) getIntent().getSerializableExtra("KEY_QUIZ");
        Log.d("Kiểm tra quiz nhận", quiz.getTen());


        listCauHoi = new ArrayList<>();
        listCauHoi = quiz.getListCauHoi();

        adapter = new LamQuizAdapter(getApplicationContext(), R.layout.row_hien_thi_cau_hoi, listCauHoi);
        binding.listViewCauHoi.setAdapter(adapter);
        CountDownTimer cdt = new CountDownTimer(quiz.getGioiHanThoiGian()*60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimeText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                nopBai();
            }
        }.start();


        binding.buttonHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cdt.cancel();
                nopBai();
            }
        });


    }

    void nopBai() {

        int diem=0;
        for (int i=0; i<quiz.getSoLuongCauHoi();i++){
            View v=binding.listViewCauHoi.getChildAt(i);
            RadioButton rd1= v.findViewById(R.id.radioButton_dapAn1);
            RadioButton rd2= v.findViewById(R.id.radioButton_dapAn2);
            RadioButton rd3= v.findViewById(R.id.radioButton_dapAn3);
            int traloi=-1;
            if (rd1.isChecked()){
                traloi=0;
            } else if (rd2.isChecked()) {
                traloi=1;
            } else if (rd3.isChecked()) {
                traloi=2;
            };
            if (traloi==-1){
                diem=diem+0;
            }else {
                if (listCauHoi.get(i).getListCauTraLoi().get(traloi).getDungSAi().equals("Đúng")){
                    diem=diem+1;
                }else {
                    diem=diem+0;
                }
            }


        }
        Log.d("Diem so", String.valueOf(diem));
    }
    void updateTimeText(long timeInMili){
        int Minute = (int) timeInMili / 1000 / 60;
        int Second = (int) timeInMili / 1000 % 60;
        binding.textViewThoiGian.setText(Minute+":"+Second);
    }
}