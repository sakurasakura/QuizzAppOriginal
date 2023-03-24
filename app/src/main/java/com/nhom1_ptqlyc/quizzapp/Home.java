package com.nhom1_ptqlyc.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.util.ArrayList;
import java.util.Comparator;

public class Home extends DrawerBaseActivity {
ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TRANG CHỦ");

        QuizCategoryAdapter adapter = new QuizCategoryAdapter(this.getApplicationContext());

        LinearLayoutManager manager = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        binding.layoutQuizCategoryCollection.setLayoutManager(manager);
        binding.layoutQuizCategoryCollection.setAdapter(adapter);

        ArrayList<QuizCategory_Home> list = new ArrayList<>();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        ArrayList<QuizWithID> listQuizHot= new ArrayList<>();

        //lay TopQuiz
        db.collection("Quiz").orderBy("luotLam", Query.Direction.DESCENDING).limit(10).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            Log.d("Lấy all quiz", "Thành công");
                            for (QueryDocumentSnapshot document:task.getResult()){
                                listQuizHot.add(new QuizWithID(document.getId(),document.toObject(Quiz.class)));
                            }
                            list.add(new QuizCategory_Home("TOP QUIZ",listQuizHot));
                            Log.d("Test", listQuizHot.get(0).getQuizID());

                            adapter.setData(list);
                           // adapter.notifyDataSetChanged();
                            //binding.layoutQuizCategoryCollection.setAdapter(adapter);

                        }
                        else {
                            Log.d("Lấy all quiz", "Thất bại");
                        }
                    }
                });


    }}

//    ArrayList<QuizWithID> layTop10(){
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//        ArrayList<QuizWithID> listQuizHot= new ArrayList<>();
//        //lay TopQuiz
//        db.collection("Quiz").orderBy("luotLam", Query.Direction.DESCENDING).limit(10).get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            Log.d("Lấy all quiz", "Thành công");
//                            for (QueryDocumentSnapshot document:task.getResult()){
//                                listQuizHot.add(new QuizWithID(document.getId(),document.toObject(Quiz.class)));
//                            }
//                        }
//                        else {
//                            Log.d("Lấy all quiz", "Thất bại");
//                        }
//                    }
//                });
//        if (listQuizHot.isEmpty()){
//            Log.e("Bất đồng bộ", "Kh lấy đc list");
//        }
//        return listQuizHot;
//    }}
    //ArrayList<QuizWithID> layCategory( String chuDe){
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//        ArrayList<QuizWithID> listQuizHot= new ArrayList<>();
//        //lay TopQuiz
//        DocumentReference reference = db.collection("Quiz").whereEqualTo("chuDe",chuDe).limit(10).get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            Log.d("Lấy all quiz", "Thành công");
//                            for (QueryDocumentSnapshot document:task.getResult()){
//                                listQuizHot.add(new QuizWithID(document.getId(),document.toObject(Quiz.class)));
//                            }
//                            return listQuizHot;
//                        }
//                        else {
//                            Log.d("Lấy all quiz", "Thất bại");
//                        }
//                        return null;
//                    }
//                });
//    }
   // ArrayList<QuizCategory_Home> getListCategory(){
//        ArrayList<QuizCategory_Home> list = new ArrayList<>();
//        ArrayList<QuizWithID> listAllQuiz= new ArrayList<>();
//        ArrayList<QuizWithID> listQuizHot= new ArrayList<>();
//        ArrayList<QuizWithID> listQuizToan= new ArrayList<>();
//        ArrayList<QuizWithID> listQuizLichSu= new ArrayList<>();
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//
//        //lay TopQuiz
//        db.collection("Quiz").orderBy("luotLam", Query.Direction.DESCENDING).limit(10).get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            Log.d("Lấy all quiz", "Thành công");
//                            for (QueryDocumentSnapshot document:task.getResult())
//                                listQuizHot.add(new QuizWithID(document.getId(),document.toObject(Quiz.class)));
//                        }
//                        else {
//                            Log.d("Lấy all quiz", "Thất bại");
//                        }
//                        return null;
//                    }
//                });
//    }
//}