package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.nhom1_ptqlyc.quizzapp.Adapters.BXHadapter;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc05LamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc06KetQuaLamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizUser;

import java.util.ArrayList;
import java.util.Comparator;

public class UC06_KetQuaLamQuiz extends DrawerBaseActivity {
    ActivityUc06KetQuaLamQuizBinding binding;
    String QuizID;
    float diem;
    String QuizUserID;
    Quiz quiz;
String tenQuiz, cdQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc06KetQuaLamQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("KẾT QUẢ");

        QuizID = getIntent().getStringExtra("KEY_QUIZ_ID");
        tenQuiz = getIntent().getStringExtra("KEY_TEN_QUIZ");
        cdQuiz = getIntent().getStringExtra("KEY_CD_QUIZ");
        diem = getIntent().getFloatExtra("KEY_DIEM", (float) 0.0);
        //diem=Float.valueOf(diemne);

        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);

        String userID = preferences.getString("id", "");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("Quiz").document(QuizID);
        quiz = new Quiz();
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                quiz = documentSnapshot.toObject(Quiz.class);
                long luotlam = quiz.getLuotLam() + 1;
                documentReference.update("luotLam", luotlam);
            }
        });

        CollectionReference collectionReference = db.collection("quiz_user");
        Task<QuerySnapshot> query = collectionReference.whereEqualTo("idNgDung", userID).whereEqualTo("idQuiz", QuizID).limit(1).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot docsnap : queryDocumentSnapshots) {
                                QuizUserID = docsnap.getId();
                                QuizUser quizUser = docsnap.toObject(QuizUser.class);
                                float highestscore = quizUser.getSoDiemCaoNhat();

                                Log.d("Diem cao nhat: ", highestscore+"");

                                if (diem > highestscore) {
                                    collectionReference.document(QuizUserID).update("soDiemCaoNhat", diem, "soDiemGanNhat", diem)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("update quizuser", "Thành công");

                                                }
                                            });
                                    binding.textViewShowDiemCaoNhat.setText("Điểm cao nhất: " + diem + ".");
                                    binding.textViewDiemLanNay.setText("Điểm lần này đạt được là: "+diem + ".");
                                } else {
                                    collectionReference.document(QuizUserID).update("soDiemGanNhat", diem)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("update quizuser", "Thành công");

                                                }
                                            });
                                    binding.textViewShowDiemCaoNhat.setText("Điểm cao nhất: " + highestscore + ".");
                                    binding.textViewDiemLanNay.setText("Điểm lần này đạt được là: "+diem + ".");
                                }
                            }
                        } else {
                            collectionReference.add(new QuizUser(QuizID,tenQuiz, cdQuiz, userID, diem, diem, 0)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("add QuizUser", "Thành công");
                                    binding.textViewShowDiemCaoNhat.setText("Điểm cao nhất: " + diem + ".");
                                    binding.textViewDiemLanNay.setText("Điểm lần này đạt được là: "+diem + ".");
                                }
                            });
                        }
                    }
                });

        ArrayList<QuizUser> listBXH = new ArrayList<>();

        Log.d("QuizId", QuizID);
        db.collection("quiz_user").whereEqualTo("idQuiz",QuizID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("Lấy bảng xếp hạng", "Thành công");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                listBXH.add(document.toObject(QuizUser.class));

                            }
                            listBXH.sort(Comparator.comparing((QuizUser::getSoDiemCaoNhat)));
                            BXHadapter bxHadapter = new BXHadapter(getApplicationContext(), listBXH, R.layout.row_bxh);
                            binding.listViewBxh.setAdapter(bxHadapter);
                        } else {
                            Log.e("Lấy bảng xếp hạng", "Lỗi");
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
}
