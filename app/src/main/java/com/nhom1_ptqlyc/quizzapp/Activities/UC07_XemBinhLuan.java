package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc06KetQuaLamQuizBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc07XemBinhLuanBinding;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.ListBinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UC07_XemBinhLuan extends DrawerBaseActivity {
    ActivityUc07XemBinhLuanBinding binding;

    String QuizID;
    String userID;
    String avatar;
LoadingDialog dialog= new LoadingDialog(this);
    BinhLuanAdapter adapter;
    ListBinhLuan listBinhLuan;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc07XemBinhLuanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("BÌNH LUẬN");
dialog.startLoading();
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
                    binding.listViewBinhLuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            openDialog_suaBL(position);
                            return true;
                        }
                    });

                }else {
                    Log.e("Lấy bình luận", "Thất bại");
                }
                adapter= new BinhLuanAdapter(getApplicationContext(),listBinhLuan);
                binding.listViewBinhLuan.setAdapter(adapter);
               // return null;
                dialog.dismissDialog();
            }
        });

        binding.buttonThemBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.startLoading();
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
                        dialog.dismissDialog();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Thêm bình luận","Thất bại");
                        dialog.dismissDialog();
                    }
                });
    }
    void openDialog_suaBL(int position) {
        final Dialog dialog2 = new Dialog(this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_longclick_binh_luan);
        Window window = dialog2.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setGravity(Gravity.CENTER);
        }
        EditText editText_bl = dialog2.findViewById(R.id.edittext_nhapBL);

        Button btn_xacnhan = dialog2.findViewById(R.id.button_xacnhanSua);
        Button btn_xoa = dialog2.findViewById(R.id.button_xoa);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String binhluan = editText_bl.getText().toString();

                if (binhluan.isEmpty()) {
                    Toast.makeText(UC07_XemBinhLuan.this, "Nhập rỗng!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dialog.startLoading();
                    DocumentReference documentReference= db.collection("Comment").document(QuizID);
                    documentReference.update("lisBinhLuan", FieldValue.arrayRemove(listBinhLuan.getListBinhLuan().get(position)));
                    documentReference.update("listBinhLuan", FieldValue.arrayUnion(new BinhLuan(userID,avatar,binhluan,true)))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(UC07_XemBinhLuan.this, "Sửa bình luận thành công!", Toast.LENGTH_SHORT).show();
                           listBinhLuan.getListBinhLuan().get(position).setNoiDung(binhluan);
                           adapter.notifyDataSetChanged();
                            dialog.dismissDialog();
                            dialog2.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismissDialog();
                            Toast.makeText(UC07_XemBinhLuan.this, "Thay đổi bình luận thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        dialog2.show();
}}