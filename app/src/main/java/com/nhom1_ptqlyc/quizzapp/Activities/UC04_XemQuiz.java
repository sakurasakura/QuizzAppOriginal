package com.nhom1_ptqlyc.quizzapp.Activities;

import android.content.Intent;
import android.os.Bundle;
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
    String QuizID= "weEDEMHSHvZIPdVUJRaz";
    Quiz quiz;
    String tenQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUc04XemQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("LÀM QUIZ");
        quiz= new Quiz();
        ArrayList<BinhLuan> listBinhLuan;

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> document= db.collection("Quiz").document(QuizID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        quiz=documentSnapshot.toObject(Quiz.class);
                    }
                });

        tenQuiz=quiz.getTen();
        binding.textViewTenQuiz.setText(tenQuiz);
        Picasso.get().load(quiz.getHinhAnhURL()).into(binding.imageViewXemQuizHinhAnh);


        binding.buttonLamQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}