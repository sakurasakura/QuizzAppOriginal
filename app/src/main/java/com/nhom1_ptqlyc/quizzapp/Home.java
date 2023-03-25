package com.nhom1_ptqlyc.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizCategoryAdapter;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityHomeBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc07XemBinhLuanBinding;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizCategory_Home;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Home extends DrawerBaseActivity {
    ActivityHomeBinding binding;
    ArrayList<QuizWithID> listQuizToan,listQuizTiengAnh,listQuizDoVui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TRANG CHỦ");
        ArrayList<QuizCategory_Home> list = new ArrayList<>();

        QuizCategoryAdapter adapter = new QuizCategoryAdapter(this);

        LinearLayoutManager manager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.layoutQuizCategoryCollection.setLayoutManager(manager);
        binding.layoutQuizCategoryCollection.setAdapter(adapter);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<QuizWithID> listQuizHot = new ArrayList<>();

        //lay TopQuiz
        db.collection("Quiz").orderBy("luotLam", Query.Direction.DESCENDING).limit(10).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("Lấy all quiz", "Thành công");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listQuizHot.add(new QuizWithID(document.getId(), document.toObject(Quiz.class)));
                    }
                    list.add(new QuizCategory_Home("TOP QUIZ", listQuizHot));
                    Log.d("Test", listQuizHot.get(0).getQuizID());

                    adapter.setData(list);
                } else {
                    Log.d("Lấy all quiz", "Thất bại");
                }
            }
        });

        ArrayList<QuizWithID>listQuiz30= new ArrayList<>();

        ArrayList<QuizCategory_Home> list2 = new ArrayList<>();

        QuizCategoryAdapter adapter2 = new QuizCategoryAdapter(this);

        LinearLayoutManager manager2 = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.layoutQuizCategoryCollection2.setLayoutManager(manager2);
        binding.layoutQuizCategoryCollection2.setAdapter(adapter2);
        listQuizToan = new ArrayList<>();
        listQuizTiengAnh = new ArrayList<>();
        listQuizDoVui = new ArrayList<>();

        //lay 30Quiz
        db.collection("Quiz").whereEqualTo("chuDe", "Toán").
        limit(30).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("Lấy 30 quiz", "Thành công");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listQuizToan.add(new QuizWithID(document.getId(), document.toObject(Quiz.class)));
                            }
                            //listQuizToan=layQuizTheoChuDe("Toán",listQuiz30);
                            listQuizTiengAnh=layQuizTheoChuDe("Tiếng Anh",listQuiz30);
                            listQuizDoVui=layQuizTheoChuDe("Đố vui",listQuiz30);
                            list2.add(new QuizCategory_Home("Chủ đề Toán học",listQuizToan));
                            //list2.add(new QuizCategory_Home("Chủ đề Tiếng ANh",listQuizTiengAnh));
                            //list2.add(new QuizCategory_Home("Chủ đề Đố vui",listQuizDoVui));
                            adapter2.setData(list2);
                            Log.d("Lấy 30 quiz", listQuizToan.get(0).getQuizID());
                        } else {
                            Log.d("Lấy all quiz", "Thất bại");
                        }
                    }
                });


    }

    ArrayList<QuizWithID> layQuizTheoChuDe(String chuDe, ArrayList<QuizWithID> list) {
        ArrayList<QuizWithID> listQuiz = new ArrayList<>();
        for (QuizWithID q : listQuiz) {
            if (q.getQuiz().getChuDe().equals(chuDe)) {
                listQuiz.add(q);
            }

        }
        return listQuiz;
    }
}
