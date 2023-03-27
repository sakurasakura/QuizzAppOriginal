package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firestore.v1.Document;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc031SuaQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.CauHoi;
import com.nhom1_ptqlyc.quizzapp.objects.CauTraLoi;
import com.nhom1_ptqlyc.quizzapp.objects.LoadingDialog;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UC03_1_SuaQuiz extends DrawerBaseActivity {
    LoadingDialog dialog;
    ActivityUc031SuaQuizBinding binding;
    Quiz quiz;
    String tenQuiz;
    String quizID;
    String chuDe;
    int gioiHanThoiGian;
    String hinhAnhURL;
    ArrayList<CauHoi> listCauHoi;
    boolean isImageReselect;
    Uri imgURI;
    int soCauHoi;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imgURI = data.getData();
                        binding.imageViewViewQuizImage.setImageURI(imgURI);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUc031SuaQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("SỬA QUIZ");
        dialog = new LoadingDialog(this);
        quiz = (Quiz) getIntent().getSerializableExtra("KEY_QUIZ");
        quizID = getIntent().getStringExtra("KEY_QUIZ_ID");
        Log.d("Kiểm tra quiz nhận", quiz.getTen());
        tenQuiz = quiz.getTen();
        chuDe = quiz.getChuDe();
        gioiHanThoiGian = quiz.getGioiHanThoiGian();
        hinhAnhURL = quiz.getHinhAnhURL();
        soCauHoi = quiz.getSoLuongCauHoi();
        listCauHoi = quiz.getListCauHoi();

        binding.edtTenQuiz.setText(tenQuiz);
        binding.edtTenChuDe.setText(chuDe);
        binding.edtThoiGian.setText(gioiHanThoiGian + "");
        isImageReselect = false;
        Picasso.get().load(quiz.getHinhAnhURL()).into(binding.imageViewViewQuizImage);
        for (int i = 0; i < soCauHoi; i++) {
            View inflater = LayoutInflater.from(this).inflate(R.layout.row_them_cau_hoi, null);
            binding.layoutCauHoi.addView(inflater, i);
            View v = ((ViewGroup) binding.layoutCauHoi).getChildAt(i);
            EditText edt_nhapCauHoi = v.findViewById(R.id.editText_nhapCauHoi);
            edt_nhapCauHoi.setText(quiz.getListCauHoi().get(i).getNoiDung());
            //
            EditText edt_ctl1 = v.findViewById(R.id.editText_nhapCauTraLoi1);
            edt_ctl1.setText(quiz.getListCauHoi().get(i).getListCauTraLoi().get(0).getNoiDung());
            RadioButton rd1_dung = v.findViewById(R.id.rd1_dung);
            RadioButton rd1_sai = v.findViewById(R.id.rd1_sai);
            if (quiz.getListCauHoi().get(i).getListCauTraLoi().get(0).getDungSAi().equals("Đúng")) {
                rd1_dung.setChecked(true);
                rd1_sai.setChecked(false);
            } else {
                rd1_dung.setChecked(false);
                rd1_sai.setChecked(true);
            }
            //
            EditText edt_ctl2 = v.findViewById(R.id.editText_nhapCauTraLoi2);
            edt_ctl2.setText(quiz.getListCauHoi().get(i).getListCauTraLoi().get(1).getNoiDung());
            RadioButton rd2_dung = v.findViewById(R.id.rd2_dung);
            RadioButton rd2_sai = v.findViewById(R.id.rd2_sai);
            if (quiz.getListCauHoi().get(i).getListCauTraLoi().get(1).getDungSAi().equals("Đúng")) {
                rd2_dung.setChecked(true);
                rd2_sai.setChecked(false);
            } else {
                rd2_dung.setChecked(false);
                rd2_sai.setChecked(true);
            }
            //
            EditText edt_ctl3 = v.findViewById(R.id.editText_nhapCauTraLoi3);
            edt_ctl3.setText(quiz.getListCauHoi().get(i).getListCauTraLoi().get(2).getNoiDung());
            RadioButton rd3_dung = v.findViewById(R.id.rd3_dung);
            RadioButton rd3_sai = v.findViewById(R.id.rd3_sai);
            if (quiz.getListCauHoi().get(i).getListCauTraLoi().get(2).getDungSAi().equals("Đúng")) {
                rd3_dung.setChecked(true);
                rd3_sai.setChecked(false);
            } else {
                rd3_dung.setChecked(false);
                rd3_sai.setChecked(true);
            }
        }
        binding.imgbtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isImageReselect = true;
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcher.launch(iGallery);
            }
        });
        binding.buttonThemCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themView_themCauHoi();
            }
        });
        binding.buttonHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.startLoading();
                listCauHoi.clear();
                themCauHoi();
                updateQuizToDB();
            }
        });


    }

    void themView_themCauHoi() {
        soCauHoi = soCauHoi + 1;
        View inflater = LayoutInflater.from(this).inflate(R.layout.row_them_cau_hoi, null);
        binding.layoutCauHoi.addView(inflater, binding.layoutCauHoi.getChildCount());
    }

    void themCauHoi() {

        for (int i = 0; i < soCauHoi; i++) {
            ArrayList<CauTraLoi> listCauTraLoi = new ArrayList<>();
            View v = ((ViewGroup) binding.layoutCauHoi).getChildAt(i);
            EditText edt_ndCauHoi = v.findViewById(R.id.editText_nhapCauHoi);
            String noiDungCauHoi = edt_ndCauHoi.getText().toString();
            //xuly cautraloi 1
            EditText edt_ndCauTraLoi = v.findViewById(R.id.editText_nhapCauTraLoi1);
            String noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            RadioGroup rg = v.findViewById(R.id.radioGroup_cauTraLoi1);
            int checked = rg.getCheckedRadioButtonId();
            String dungsai = ((RadioButton) v.findViewById(checked)).getText().toString();
            CauTraLoi ctl = new CauTraLoi(noiDungCauTraLoi, dungsai);
            listCauTraLoi.add(ctl);

            //xulyb cautraloi2
            edt_ndCauTraLoi = v.findViewById(R.id.editText_nhapCauTraLoi2);
            noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            rg = v.findViewById(R.id.radioGroup_cauTraLoi2);
            checked = rg.getCheckedRadioButtonId();
            dungsai = ((RadioButton) v.findViewById(checked)).getText().toString();
            ctl = new CauTraLoi(noiDungCauTraLoi, dungsai);
            listCauTraLoi.add(ctl);

            //xulyb cautraloi3
            edt_ndCauTraLoi = v.findViewById(R.id.editText_nhapCauTraLoi3);
            noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            rg = v.findViewById(R.id.radioGroup_cauTraLoi3);
            checked = rg.getCheckedRadioButtonId();
            dungsai = ((RadioButton) v.findViewById(checked)).getText().toString();
            ctl = new CauTraLoi(noiDungCauTraLoi, dungsai);
            listCauTraLoi.add(ctl);

            listCauHoi.add(new CauHoi(noiDungCauHoi, listCauTraLoi));
            Log.d("Dap an cau", listCauTraLoi.get(0).getNoiDung());
        }

    }

    void updateQuizToDB() {
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);

        String tenQuiz = ((EditText) binding.edtTenQuiz).getText().toString();
        String userID = preferences.getString("id", "");
        long luotlam = quiz.getLuotLam();
        float rating = 0;
        String tenDeTai = ((EditText) binding.edtTenChuDe).getText().toString();
        gioiHanThoiGian = Integer.parseInt(binding.edtThoiGian.getText().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("Quiz").document(quizID);
        if (isImageReselect) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference photoRef = storageReference.child(quiz.getHinhAnhURL());
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
                    hinhAnhURL = uri.toString();
                    Log.d("complete uri", hinhAnhURL);
                    reference.update("chuDe", chuDe, "gioiHanThoiGian", gioiHanThoiGian, "hinhAnhURL", hinhAnhURL, "listCauHoi", listCauHoi, "soLuongCauHoi", soCauHoi, "ten", tenQuiz);
                    dialog.dismissDialog();
                    Intent intent = new Intent(getApplicationContext(), UC04_XemQuiz.class);
                    intent.putExtra("KEY_QUIZ_WITH_ID", quizID);
                    startActivity(intent);
                }
            });

        } else {
            Log.d("úpdate quiz", listCauHoi.get(1).getListCauTraLoi().get(2).getNoiDung().toString());
            reference.update("chuDe", chuDe, "gioiHanThoiGian", gioiHanThoiGian, "listCauHoi", listCauHoi, "soLuongCauHoi", soCauHoi, "ten", tenQuiz);
            dialog.dismissDialog();
            Toast.makeText(UC03_1_SuaQuiz.this, "Update thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UC04_XemQuiz.class);
            intent.putExtra("KEY_QUIZ_WITH_ID", quizID);
            startActivity(intent);
        }

    }
}