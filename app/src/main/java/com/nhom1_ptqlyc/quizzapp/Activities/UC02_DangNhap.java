package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nhom1_ptqlyc.quizzapp.Home;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UC02_DangNhap extends AppCompatActivity {
    EditText edittext_username, edittext_pass;
    Button button_dangNhap;
    String id="";
    String rightpass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc02_dang_nhap);
        anhxa();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        button_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edittext_username.getText().toString();
                String pass = edittext_pass.getText().toString().trim();
                List<User> userList=new ArrayList<>();
                CollectionReference collectionReference = db.collection("users");
                Task<QuerySnapshot> query = collectionReference.whereEqualTo("username", username).limit(1).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    for (QueryDocumentSnapshot docsnap: queryDocumentSnapshots) {
                                        id=docsnap.getId();
                                        userList.add(docsnap.toObject(User.class));
                                    }
                                    if (userList.get(0).getPassword().equals(pass)){
                                        saveUserPre(userList.get(0), id);
                                        Toast.makeText(UC02_DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(UC02_DangNhap.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(UC02_DangNhap.this, "Tên đăng nhập không tồn tại", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UC02_DangNhap.this, "Lỗi truy vấn dữ liệu", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
    void anhxa(){
        button_dangNhap=findViewById(R.id.button_dangNhap);
        edittext_username=findViewById(R.id.edittext_username);
        edittext_pass=findViewById(R.id.edittext_pass);
    }
    void saveUserPre(User user, String id){
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        Log.d("birthday", user.getBirthday()+"");
        editor.putString("birthday", user.getBirthday());
        editor.putString("gender", user.getGender());
        editor.putString("avatar", user.getAvatar());
        editor.putBoolean("isAdmin", user.isAdmin());
        editor.putString("id",id);
        editor.commit();

    }
}