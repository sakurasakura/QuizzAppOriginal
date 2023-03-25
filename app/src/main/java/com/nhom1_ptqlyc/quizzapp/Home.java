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
    ArrayList<QuizWithID> listQuizToan, listQuizTiengAnh, listQuizDoVui;

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
        listQuizToan = new ArrayList<>();
        listQuizTiengAnh = new ArrayList<>();
        listQuizDoVui = new ArrayList<>();
        //lay quiz Toan

        ArrayList<QuizCategory_Home> list2 = new ArrayList<>();

        QuizCategoryAdapter adapter2 = new QuizCategoryAdapter(this);

        LinearLayoutManager manager2 = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.layoutQuizCategoryCollection2.setLayoutManager(manager2);
        binding.layoutQuizCategoryCollection2.setAdapter(adapter2);


        //lay quiz Toan
        db.collection("Quiz").whereEqualTo("chuDe", "Toán").limit(10).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("Lấy 10 quiz Toán", "Thành công");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listQuizToan.add(new QuizWithID(document.getId(), document.toObject(Quiz.class)));
                    }
                    list2.add(new QuizCategory_Home("Quiz toán", listQuizToan));
                    adapter2.setData(list2);
                    Log.d("Lấy 10 quiz Toán", listQuizToan.get(0).getQuizID());
                } else {
                    Log.d("Lấy 10 quiz Toán", "Thất bại");
                }
            }
        });
        //lay quiz TiengAnh

        ArrayList<QuizCategory_Home> list3 = new ArrayList<>();

        QuizCategoryAdapter adapter3 = new QuizCategoryAdapter(this);

        LinearLayoutManager manager3 = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.layoutQuizCategoryCollection3.setLayoutManager(manager3);
        binding.layoutQuizCategoryCollection3.setAdapter(adapter3);


        db.collection("Quiz").whereEqualTo("chuDe", "Tiếng Anh").limit(10).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("Lấy 10 quiz Tiếng Anh", "Thành công");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listQuizTiengAnh.add(new QuizWithID(document.getId(), document.toObject(Quiz.class)));
                    }
                    list3.add(new QuizCategory_Home("Quiz tiếng anh", listQuizTiengAnh));
                    adapter3.setData(list3);
                    Log.d("Lấy 10 quiz Tiếng Anh", listQuizTiengAnh.get(0).getQuizID());
                } else {
                    Log.d("Lấy 10 quiz Tiếng Anh", "Thất bại");
                }
            }
        });
        ArrayList<QuizCategory_Home> list4 = new ArrayList<>();

        QuizCategoryAdapter adapter4 = new QuizCategoryAdapter(this);

        LinearLayoutManager manager4 = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.layoutQuizCategoryCollection4.setLayoutManager(manager4);
        binding.layoutQuizCategoryCollection4.setAdapter(adapter4);


        //lay quiz Đố vui
        db.collection("Quiz").whereEqualTo("chuDe", "Đố vui").limit(10).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("Lấy 10 quiz Đố vui", "Thành công");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listQuizDoVui.add(new QuizWithID(document.getId(), document.toObject(Quiz.class)));
                    }
                    list4.add(new QuizCategory_Home("Quiz đố vui", listQuizHot));
                    adapter3.setData(list3);
                    Log.d("Lấy 10 quiz Đố vui", listQuizDoVui.get(0).getQuizID());
                } else {
                    Log.d("Lấy 10 quiz Đố vui", "Thất bại");
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
