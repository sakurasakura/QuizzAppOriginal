package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizCardAdapter2;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc09TimKiemBinding;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;

import java.util.ArrayList;
import java.util.List;

public class UC09_TimKiem extends DrawerBaseActivity {
    ActivityUc09TimKiemBinding binding;
    FirebaseFirestore db;
    ArrayList<QuizWithID> listAllQuiz;
    ArrayList<QuizWithID> listQuizSearch;
    QuizCardAdapter2 adapter;
    LoadingDialog dialog = new LoadingDialog(this);

    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUc09TimKiemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TÌM KIẾM");
        dialog.startLoading();
        db = FirebaseFirestore.getInstance();
        listAllQuiz= new ArrayList<>();
        listQuizSearch = new ArrayList<>();

        adapter = new QuizCardAdapter2(listQuizSearch,listAllQuiz,getApplicationContext());
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
        binding.layoutKqTimQuiz.setLayoutManager(manager);
        binding.layoutKqTimQuiz.setAdapter(adapter);

        readData(new FirestoreCallback() {
            @Override
            public void onCallback(ArrayList<QuizWithID> list) {

                binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.d("KiemTra", listAllQuiz.get(0).getQuizID());
                        adapter.getFilter().filter(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.d("KiemTra", listAllQuiz.get(0).getQuizID());
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });
                Log.d("listAllQuiz", listAllQuiz.isEmpty()+"");
            }
        });
   }

   private void readData(FirestoreCallback firestoreCallback) {
       db.collection("Quiz").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if (task.isSuccessful()) {
                   Log.d("Lấy tất cả quiz", "Thành công");
                   for (QueryDocumentSnapshot doc : task.getResult()) {
                       listAllQuiz.add(new QuizWithID(doc.getId(), doc.toObject(Quiz.class)));
                       Log.d("Lấy tất cả quiz", listAllQuiz.toString());
                   }
                   adapter.notifyDataSetChanged();
                   dialog.dismissDialog();
                   firestoreCallback.onCallback(listAllQuiz);
               } else {
                   Log.d("Lấy tất cả quiz", "Thất bại");
                   dialog.dismissDialog();
               }
           }
       });
   }

   private interface FirestoreCallback {
        void onCallback(ArrayList<QuizWithID> list);
   }

}