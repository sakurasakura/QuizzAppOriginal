package com.nhom1_ptqlyc.quizzapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc04XemQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UC04_XemQuiz extends DrawerBaseActivity {
    ActivityUc04XemQuizBinding binding;
    String QuizID= "snaW7pTlAJlGBVmCMYq6";
    Quiz quiz;
    String tenQuiz;
    String luotLam;
    String rating;
    String thoiGian;
    String chuDe;
    String nguoiTao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUc04XemQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");
        quiz= new Quiz();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> document= db.collection("Quiz").document(QuizID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        quiz=documentSnapshot.toObject(Quiz.class);
                        Log.e("Lỗi lấy quiz", quiz.getNguoiTao());
                        setQuiz(quiz);
                    }
                });



        binding.buttonLamQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UC05_LamQuiz.class);
                intent.putExtra("KEY_QUIZ_ID", QuizID);
                intent.putExtra("KEY_QUIZ",quiz);
                if (quiz==null){
                    Log.e("button làm quiz", "quiz null");
                }else {
                    startActivity(intent);
                }
            }
        });
        binding.clickXemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UC07_XemBinhLuan.class);
                intent.putExtra("KEY_QUIZ_ID",QuizID);
                startActivity(intent);
            }
        });
    }
    void setQuiz(Quiz quiz){
        nguoiTao= quiz.getNguoiTao();
        Log.d("Id ng tao:", nguoiTao+"");
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> document2= db.collection("users").document(nguoiTao).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        nguoiTao=documentSnapshot.get("username",String.class);
                        tenQuiz=quiz.getTen();
                        luotLam=String.valueOf(quiz.getLuotLam());
                        rating=String.valueOf(quiz.getRating());
                        thoiGian=String.valueOf(quiz.getGioiHanThoiGian());
                        chuDe=quiz.getChuDe();
                        binding.textViewTenQuiz.setText(tenQuiz);
                        binding.textViewUser.setText(nguoiTao);
                        binding.textViewLuotLam.setText(luotLam);
                        binding.textViewTimer.setText(thoiGian+" phút");
                        binding.textViewChuDe.setText(chuDe);
                        Picasso.get().load(quiz.getHinhAnhURL()).into(binding.imageViewXemQuizHinhAnh);
                    }
                });





    }
}