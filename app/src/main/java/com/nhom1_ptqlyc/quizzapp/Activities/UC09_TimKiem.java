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

public class UC09_TimKiem extends DrawerBaseActivity {
    ActivityUc09TimKiemBinding binding;
    FirebaseFirestore db;
    LoadingDialog dialog = new LoadingDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUc09TimKiemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TÌM KIẾM");
        dialog.startLoading();
        db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Quiz");

        ArrayList<QuizWithID> listAllQuiz= new ArrayList<>();
        ArrayList<QuizWithID> listQuizSearch= new ArrayList<>();

        QuizCardAdapter2 adapter= new QuizCardAdapter2(listQuizSearch,listAllQuiz,getApplicationContext());
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
        binding.layoutKqTimQuiz.setLayoutManager(manager);
        binding.layoutKqTimQuiz.setAdapter(adapter);

        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                                Log.d("Lấy tất cả quiz", "Thành công");
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    listAllQuiz.add(new QuizWithID(doc.getId(), doc.toObject(Quiz.class)));
                                }
                                adapter.notifyDataSetChanged();
                                dialog.dismissDialog();
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
                            } else {
                                Log.d("Lấy tất cả quiz", "Thất bại");
                                dialog.dismissDialog();
                            }
            }
        });

   }


}