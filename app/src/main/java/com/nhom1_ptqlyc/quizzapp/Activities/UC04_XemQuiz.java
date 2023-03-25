package com.nhom1_ptqlyc.quizzapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import com.nhom1_ptqlyc.quizzapp.Home;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc04XemQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UC04_XemQuiz extends DrawerBaseActivity {
    ActivityUc04XemQuizBinding binding;
    String QuizID;

    QuizWithID quizWithID;
    Quiz quiz;
    String tenQuiz;
    String luotLam;
    String rating;
    String thoiGian;
    String chuDe;
    String nguoiTao;
 String thisUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc04XemQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");

        SharedPreferences preferences= getSharedPreferences("user_data",MODE_PRIVATE);
        thisUser= preferences.getString("username","");
//        quizWithID = new QuizWithID();
//        quizWithID = (QuizWithID) getIntent().getSerializableExtra("KEY_QUIZ_WITH_ID", QuizWithID.class);
        QuizID = getIntent().getStringExtra("KEY_QUIZ_WITH_ID");
//        Log.d("id", id);
//        return;
  //     quiz = quizWithID.getQuiz();
     //   QuizID = quizWithID.getQuizID();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> document = db.collection("Quiz").document(QuizID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        quiz = documentSnapshot.toObject(Quiz.class);
                        Log.e("Lỗi lấy quiz", quiz.getNguoiTao());
                        setQuiz(quiz);
                    }
                });


        binding.buttonLamQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UC05_LamQuiz.class);
                intent.putExtra("KEY_QUIZ_ID", QuizID);
                intent.putExtra("KEY_QUIZ", quiz);
                if (quiz == null) {
                    Log.e("button làm quiz", "quiz null");
                } else {
                    startActivity(intent);
                }
            }
        });
        binding.clickXemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UC07_XemBinhLuan.class);
                intent.putExtra("KEY_QUIZ_ID", QuizID);
                startActivity(intent);
            }
        });
        binding.btnSuaQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UC03_1_SuaQuiz.class);
                intent.putExtra("KEY_QUIZ_ID", QuizID);
                intent.putExtra("KEY_QUIZ", quiz);
                if (quiz == null) {
                    Log.e("button làm quiz", "quiz null");
                } else {
                    startActivity(intent);
                }
            }
        });
        binding.btnXoaQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UC04_XemQuiz.this).setTitle("Xác nhận xóa").setMessage("Bạn có chắc chắn muốn xóa không?\nQuiz đã xóa không thể lấy lại.")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("Quiz").document(QuizID).delete();
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Đã xóa quiz", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }


    void setQuiz(Quiz quiz) {
        nguoiTao = quiz.getNguoiTao();
        Log.d("Id ng tao:", nguoiTao + "");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> document2 = db.collection("users").document(nguoiTao).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        nguoiTao = documentSnapshot.get("username", String.class);
                        tenQuiz = quiz.getTen();
                        luotLam = String.valueOf(quiz.getLuotLam());
                        rating = String.valueOf(quiz.getRating());
                        thoiGian = String.valueOf(quiz.getGioiHanThoiGian());
                        chuDe = quiz.getChuDe();
                        binding.textViewTenQuiz.setText(tenQuiz);
                        binding.textViewUser.setText(nguoiTao);
                        if (nguoiTao.equals(thisUser)){
                            binding.layoutCuaNguoiTao.setVisibility(View.VISIBLE);
                        }
                        binding.textViewLuotLam.setText(luotLam);
                        binding.textViewTimer.setText(thoiGian + " phút");
                        binding.textViewChuDe.setText(chuDe);
                        Picasso.get().load(quiz.getHinhAnhURL()).into(binding.imageViewXemQuizHinhAnh);
                    }
                });


    }
}