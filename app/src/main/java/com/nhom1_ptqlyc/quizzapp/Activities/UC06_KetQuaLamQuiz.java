package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc05LamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc06KetQuaLamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizUser;

import java.util.ArrayList;

public class UC06_KetQuaLamQuiz extends DrawerBaseActivity {
ActivityUc06KetQuaLamQuizBinding binding;
String QuizID;
float diem;
String QuizUserID;
Quiz quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc06KetQuaLamQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("KẾT QUẢ");

        QuizID= getIntent().getStringExtra("KEY_QUIZ_ID");
        diem = getIntent().getFloatExtra("KEY_DIEM",(float) 0.0);
        //diem=Float.valueOf(diemne);

        SharedPreferences preferences=getSharedPreferences("user_data",MODE_PRIVATE);

        String userID = preferences.getString("id","");
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        DocumentReference documentReference= db.collection("Quiz").document(QuizID);
        quiz= new Quiz();
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                quiz= documentSnapshot.toObject(Quiz.class);
                long luotlam = quiz.getLuotLam()+1;
                documentReference.update("luotLam", luotlam);
            }
        });

        CollectionReference collectionReference = db.collection("quiz_user");
        Task<QuerySnapshot> query=collectionReference.whereEqualTo("idNgDung",userID).whereEqualTo("idQuiz",QuizID).limit(1).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            for (QueryDocumentSnapshot docsnap: queryDocumentSnapshots) {
                                QuizUserID=docsnap.getId();
                                QuizUser quizUser= docsnap.toObject(QuizUser.class);
                                float highestscore= quizUser.getSoDiemCaoNhat();
                                if (diem>highestscore){
                                    collectionReference.document(QuizUserID).update("soDiemCaoNhat",diem, "soDiemGanNhat",diem)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("update quizuser","Thành công");
                                                }
                                            });
                                }else {
                                    collectionReference.document(QuizUserID).update("soDiemGanNhat",diem)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("update quizuser","Thành công");
                                                }
                                            });
                                }
                            }
                        }else {
                            collectionReference.add(new QuizUser(QuizID,userID,diem,diem,0)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("add QuizUser", "Thành công");
                                }
                            });
                        }
                    }
                });

        db.collection("quiz_user").orderBy("soDiemCaoNhat", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d( document.getId() + " => " + document.getData());
                            }
                        } else {
                            //Log.d("Error getting documents: ", task.getException());
                        }
                    }
                });


       // binding.listViewBxh
    }
}
