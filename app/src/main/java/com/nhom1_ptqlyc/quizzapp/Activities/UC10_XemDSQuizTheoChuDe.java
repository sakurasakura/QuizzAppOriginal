package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizCardAdapter2;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc10XemDsquizTheoChuDeBinding;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;

import java.util.ArrayList;


public class UC10_XemDSQuizTheoChuDe extends DrawerBaseActivity {
ActivityUc10XemDsquizTheoChuDeBinding binding;
String chuDe;
ArrayList<QuizWithID> list;
LoadingDialog dialog=new LoadingDialog(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUc10XemDsquizTheoChuDeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list= new ArrayList<>();
        chuDe=getIntent().getStringExtra("KEY_CHU_DE");
        allocateActivityName("Danh sách quiz theo chủ đề: "+ chuDe);
dialog.startLoading();
        QuizCardAdapter2 adapter = new QuizCardAdapter2();
        adapter.setData(list,getApplicationContext());
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2);
        binding.rvQuizTheoChuDe.setLayoutManager(manager);
        binding.rvQuizTheoChuDe.setAdapter(adapter);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("Quiz").whereEqualTo("chuDe",chuDe).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot q: task.getResult()
                                 ) {
                                list.add(new QuizWithID(q.getId(),q.toObject(Quiz.class)));
                            }
                            adapter.notifyDataSetChanged();
                            dialog.dismissDialog();
                        }
                        else {
                            Toast.makeText(UC10_XemDSQuizTheoChuDe.this, "Lấy quiz thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}