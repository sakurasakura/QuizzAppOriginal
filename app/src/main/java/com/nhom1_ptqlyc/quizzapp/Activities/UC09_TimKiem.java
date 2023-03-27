package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizCategoryAdapter2;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc09TimKiemBinding;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizCategory_Home;
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

        db = FirebaseFirestore.getInstance();

        ArrayList<QuizWithID> listQuiz = new ArrayList<>();
        ArrayList<QuizWithID> listQuiz_kq = new ArrayList<>();
        ArrayList<QuizCategory_Home> listQuiz_hienThi = new ArrayList<>();
        listQuiz_hienThi.add(new QuizCategory_Home("Kết quả tìm kiếm quiz theo tên", listQuiz_kq));
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        QuizCategoryAdapter2 adapter2 = new QuizCategoryAdapter2(getApplicationContext(), listQuiz_hienThi);

        binding.layoutKqTimQuiz.setLayoutManager(manager);
        binding.layoutKqTimQuiz.setAdapter(adapter2);
        binding.layoutKqTimQuiz.setVisibility(View.GONE);

        String idQuiz = "";


        binding.imgbtnSearchTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                dialog.startLoading();
                String keyword = binding.edtTimKiemTheoTen.getText().toString();
                if (keyword.isEmpty()) {
                    Toast.makeText(UC09_TimKiem.this, "Nhập trống!", Toast.LENGTH_SHORT).show();
                    dialog.dismissDialog();
                    return;
                } else {
                    db.collection("Quiz").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d("Lấy tất cả quiz", "Thành công");
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    listQuiz.add(new QuizWithID(doc.getId(), doc.toObject(Quiz.class)));
                                }
                                for (int i = 0; i < listQuiz.size(); i++) {
                                    if (listQuiz.get(i).getQuiz().getTen().contains(keyword)) {
                                        listQuiz_kq.add(listQuiz.get(i));
                                    }
                                }
                                listQuiz_hienThi.get(0).setQuizWithIDS(listQuiz_kq);
                                Log.d("Lây kq tìm tên", listQuiz_kq.isEmpty() + "");
                                adapter2.setData(listQuiz_hienThi);
                                binding.layoutKqTimQuiz.setVisibility(View.VISIBLE);
                                Log.d("Lấy tất cả quiz", listQuiz.get(0).getQuizID());
                                dialog.dismissDialog();
                            } else {
                                Log.d("Lấy tất cả quiz", "Thất bại");
                                dialog.dismissDialog();
                            }
                        }
                    });

                }
            }
        });
        binding.imgbtnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                dialog.startLoading();
                String keyword = binding.edtTimKiemUser.getText().toString();
                if (keyword.isEmpty()) {
                    Toast.makeText(UC09_TimKiem.this, "Nhập trống!", Toast.LENGTH_SHORT).show();
                    dialog.dismissDialog();
                    return;
                } else {
                    db.collection("Quiz").whereEqualTo("nguoiTao",keyword).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d("Lấy quiz của user", "Thành công");
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    listQuiz_kq.add(new QuizWithID(doc.getId(), doc.toObject(Quiz.class)));
                                }
                                listQuiz_hienThi.get(0).setQuizWithIDS(listQuiz_kq);
                                Log.d("Lây kq tìm tên", listQuiz_kq.isEmpty() + "");
                                adapter2.setData(listQuiz_hienThi);
                                binding.layoutKqTimQuiz.setVisibility(View.VISIBLE);
                                Log.d("Lấy tất cả quiz", listQuiz.get(0).getQuizID());
                                dialog.dismissDialog();
                            } else {
                                Log.d("Lấy tất cả quiz", "Thất bại");
                                dialog.dismissDialog();
                            }
                        }
                    });

                }
            }
        });
    }
}