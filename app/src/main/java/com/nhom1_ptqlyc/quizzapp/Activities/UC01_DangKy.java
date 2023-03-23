package com.nhom1_ptqlyc.quizzapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.User;

import java.util.HashMap;
import java.util.Map;

public class UC01_DangKy extends AppCompatActivity {
    EditText edittext_nhapTen,edittext_nhapNgaySinh,edittext_nhapPass;
    RadioGroup radiogroup_gioiTinh;
//    RadioButton radiobutton_nam, radiobutton_nu;
    TextView textview_dangNhap;
    Button button_dangKy;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc01_dangky);
        anhxa();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= edittext_nhapTen.getText().toString();
                String birth= edittext_nhapNgaySinh.getText().toString();
                String pw= edittext_nhapPass.getText().toString();
                int checked = radiogroup_gioiTinh.getCheckedRadioButtonId();
                RadioButton checkedbutton= findViewById(checked);
                String gender= checkedbutton.getText().toString();

                if (username.isEmpty() || birth.isEmpty() || pw.isEmpty()){
                    Toast.makeText(UC01_DangKy.this, "Nhập thiếu dữ liệu!", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("username", username.trim());
                    map.put("password", pw.trim());
                    map.put("isAdmin", false);
                    map.put("birth", birth);
                    map.put("gender", gender);
                    map.put("listQuiz", null);
                    map.put("avatar", null);
                    map.put("isActive", true);

                    DocumentReference documentReference=db.collection("users").document(username);
                    documentReference.set(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(UC01_DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UC01_DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        textview_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UC02_DangNhap.class);
                startActivity(intent);
            }
        });
    }
    void anhxa(){
        edittext_nhapTen=findViewById(R.id.edittext_nhapTen);
        edittext_nhapNgaySinh=findViewById(R.id.edittext_nhapNgaySinh);
        edittext_nhapPass=findViewById(R.id.edittext_nhapPass);
        radiogroup_gioiTinh=findViewById(R.id.radiogroup_gioiTinh);
        textview_dangNhap=findViewById(R.id.textview_dangNhap);
        button_dangKy=findViewById(R.id.button_dangky);
    }
}
