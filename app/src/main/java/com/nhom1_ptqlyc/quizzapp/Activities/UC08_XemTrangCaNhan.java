package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.v1.Document;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizDaLamAdapter;
import com.nhom1_ptqlyc.quizzapp.Adapters.QuizDaTaoAdapter;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc07XemBinhLuanBinding;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc08XemTrangCaNhanBinding;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.nhom1_ptqlyc.quizzapp.objects.QuizUser;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UC08_XemTrangCaNhan extends DrawerBaseActivity {
    ActivityUc08XemTrangCaNhanBinding binding;
    ArrayList<QuizWithID> listQuizTao;
    LoadingDialog dialog;
    FirebaseFirestore db;
    String id, username, pass;
    SharedPreferences sharedPreferences;
    Uri imgURI;
    String hinhAnh;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                imgURI = data.getData();
                binding.avatar.setImageURI(imgURI);
                dialog.startLoading();
                DocumentReference reference = db.collection("users").document(username);
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference photoRef = storageReference.child("image/" + username + '/' + "avatar");
                UploadTask uploadTask = photoRef.putFile(imgURI);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("Upload img", "Succeed");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Upload img", "Fail");
                    }
                });
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            Log.e("getURL", "Fail");
                        }
                        return photoRef.getDownloadUrl();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        hinhAnh = uri.toString();
                        Log.d("complete uri", hinhAnh);
                        reference.update("avatar", hinhAnh);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("avatar", hinhAnh);
                        dialog.dismissDialog();
                    }
                });
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc08XemTrangCaNhanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TRANG CÁ NHÂN");

        listQuizTao = new ArrayList<>();
        id = "";
        dialog = new LoadingDialog(this);

        QuizDaTaoAdapter adapter1 = new QuizDaTaoAdapter(getApplicationContext(), listQuizTao);
        binding.lvQuizDaTao.setAdapter(adapter1);

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
        String birthday = sharedPreferences.getString("birthday", "");
        String gender = sharedPreferences.getString("gender", "Nam");
        String avaLink = sharedPreferences.getString("avatar", "");
        if (avaLink != "") Picasso.get().load(avaLink).into(binding.avatar);
        String info;
        if (isAdmin) {
            info = "Username: " + username + "\nNgày sinh" + birthday + "\nGiới tính: " + gender + "\nQuyền: Admin";
        } else {
            info = "Username: " + username + "\nNgày sinh: " + birthday + "\nGiới tính: " + gender + "\nQuyền: Người dùng";
        }
        binding.tvUserInfo.setText(info);

        pass = sharedPreferences.getString("password", "");
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Quiz");
        collectionReference.whereEqualTo("nguoiTao", username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("Lấy quiz đã tạo", "Thành công");
                if (!queryDocumentSnapshots.isEmpty()) {
                    Log.d("Lấy quiz đã tạo", "Có phần tử");
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        id = doc.getId();
                        listQuizTao.add(new QuizWithID(id, doc.toObject(Quiz.class)));
                    }
                    adapter1.notifyDataSetChanged();
                } else {
                    binding.lvQuizDaTao.setVisibility(View.GONE);
                    Log.e("Lấy quiz đã tạo", "Trống");
                    return;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Lấy quiz đã tạo", "Thất bại");
                return;
            }
        });

        ArrayList<QuizUser> list2 = new ArrayList<>();

        QuizDaLamAdapter adapter2 = new QuizDaLamAdapter(getApplicationContext(), list2);
        binding.lvQuizDaLam.setAdapter(adapter2);

        CollectionReference collectionReference1 = db.collection("quiz_user");
        collectionReference1.whereEqualTo("idNgDung", username).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("Lấy quiz đã làm", "Thành công");
                if (!queryDocumentSnapshots.isEmpty()) {
                    Log.d("Lấy quiz đã làm", "Có phần tử");
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        list2.add(doc.toObject(QuizUser.class));
                    }
                    adapter2.notifyDataSetChanged();
                } else {
                    binding.lvQuizDaLam.setVisibility(View.GONE);
                    Log.e("Lấy quiz đã làm", "Trống");
                    return;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Lấy quiz đã làm", "Thất bại");
                return;
            }
        });
        binding.clickChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(iGallery,GALLERY_REQ_CODE);
                launcher.launch(iGallery);
            }
        });
        binding.clickToChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_suaTT();
            }
        });

    }

    void openDialog_suaTT() {
        final Dialog dialog2 = new Dialog(this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setContentView(R.layout.dialog_sua_thong_tin);
        Window window = dialog2.getWindow();
        if (window == null) {
            return;
        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setGravity(Gravity.CENTER);
        }
        EditText editText_oldpass = dialog2.findViewById(R.id.edittext_nhapOldPass);
        EditText editText_newpass = dialog2.findViewById(R.id.edittext_nhapOldPass);

        Button btn_xacnhan = dialog2.findViewById(R.id.button_xacnhan);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = editText_oldpass.getText().toString();
                String newPass = editText_newpass.getText().toString();

                if (!oldPass.equals(pass)) {
                    Toast.makeText(UC08_XemTrangCaNhan.this, "Nhập sai mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dialog.startLoading();
                    db.collection("users").document(username).update("password", newPass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(UC08_XemTrangCaNhan.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor edit_pass = sharedPreferences.edit();
                            edit_pass.putString("password", newPass);
                            dialog.dismissDialog();
                            dialog2.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismissDialog();
                            Toast.makeText(UC08_XemTrangCaNhan.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        dialog2.show();
    }

}