package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nhom1_ptqlyc.quizzapp.Adapters.BinhLuanAdapter;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc06KetQuaLamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc07XemBinhLuanBinding;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.ListBinhLuan;

import java.util.ArrayList;

public class UC07_XemBinhLuan extends DrawerBaseActivity {
    ActivityUc07XemBinhLuanBinding binding;

    String QuizID;
    String userID;
    String avatar;

    BinhLuanAdapter adapter;
    ListBinhLuan listBinhLuan;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc07XemBinhLuanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("BÌNH LUẬN");

        getIntent();
        QuizID = getIntent().getStringExtra("KEY_QUIZ_ID");
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        userID = preferences.getString("id", "");
        avatar = preferences.getString("avatar","");
        listBinhLuan=new ListBinhLuan(new ArrayList<>());

        db=FirebaseFirestore.getInstance();

        DocumentReference reference=db.collection("Comment").document(QuizID);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    listBinhLuan=task.getResult().toObject(ListBinhLuan.class);
                    if (!listBinhLuan.getListBinhLuan().isEmpty()){
                        Log.d("ktra listBinhLuan", listBinhLuan.getListBinhLuan().get(0).getNoiDung());

                    }

                }else {
                    Log.e("Lấy bình luận", "Thất bại");
                }
                adapter= new BinhLuanAdapter(getApplicationContext(),listBinhLuan);
                binding.listViewBinhLuan.setAdapter(adapter);
               // return null;
            }
        });

        binding.buttonThemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = binding.editTextThemBinhLuan.getText().toString();
                if (noidung.isEmpty()){
                    Toast.makeText(UC07_XemBinhLuan.this, "Nội dung nhập trống", Toast.LENGTH_SHORT).show();
                }else {
                    BinhLuan binhLuan = new BinhLuan(userID,avatar,noidung,true);
                    listBinhLuan.getListBinhLuan().add(binhLuan);
                    adapter.notifyDataSetChanged();
                    pushCommentToDB(binhLuan);
                }
                binding.editTextThemBinhLuan.getText().clear();
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }
    void pushCommentToDB(BinhLuan binhLuan){
        DocumentReference reference=db.collection("Comment").document(QuizID);
        reference.update("listBinhLuan", FieldValue.arrayUnion(binhLuan))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Thêm bình luận", "Thành công");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Thêm bình luận","Thất bại");
                    }
                });
    }
}