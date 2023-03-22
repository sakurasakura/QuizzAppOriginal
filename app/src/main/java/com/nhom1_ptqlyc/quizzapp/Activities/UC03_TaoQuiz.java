package com.nhom1_ptqlyc.quizzapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.type.DateTime;
import com.nhom1_ptqlyc.quizzapp.DrawerBaseActivity;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.databinding.ActivityUc03TaoQuizBinding;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.CauHoi;
import com.nhom1_ptqlyc.quizzapp.objects.CauTraLoi;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;

import java.io.IOException;
import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UC03_TaoQuiz extends DrawerBaseActivity {
    ActivityUc03TaoQuizBinding binding;
    ArrayList<CauHoi> listCauHoi;
    int soCauHoi;
    final int GALLERY_REQ_CODE=1000;
    Uri imgURI;
    String hinhAnh;
    String userID;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==Activity.RESULT_OK){
                        Intent data=result.getData();
                        imgURI= data.getData();
                        binding.imageViewViewQuizImage.setImageURI(imgURI);
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUc03TaoQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        allocateActivityName("TẠO QUIZ");

        binding.imgbtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
               iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(iGallery,GALLERY_REQ_CODE);
                launcher.launch(iGallery);
                //selectImg();
            }
        });

        soCauHoi=0;
        listCauHoi = new ArrayList<>();

        binding.buttonThemCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themView_themCauHoi();
            }
        });
        binding.buttonHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themCauHoi();
                themQuiz();
            }
        });

    }
    void themView_themCauHoi(){
        soCauHoi=soCauHoi+1;
        View inflater = LayoutInflater.from(this).inflate(R.layout.row_them_cau_hoi,null);
        binding.layoutCauHoi.addView(inflater,binding.layoutCauHoi.getChildCount());
    }
    void themCauHoi(){

        for (int i=0;i<soCauHoi;i++){
            ArrayList<CauTraLoi> listCauTraLoi= new ArrayList<>();
            View v = ((ViewGroup) binding.layoutCauHoi).getChildAt(i);
            EditText edt_ndCauHoi= v.findViewById(R.id.editText_nhapCauHoi);
            String noiDungCauHoi = edt_ndCauHoi.getText().toString();
            //xuly cautraloi 1
            EditText edt_ndCauTraLoi= v.findViewById(R.id.editText_nhapCauTraLoi1);
            String noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            RadioGroup rg = v.findViewById(R.id.radioGroup_cauTraLoi1);
            int checked = rg.getCheckedRadioButtonId();
            String dungsai = ((RadioButton)v.findViewById(checked)).getText().toString();
            CauTraLoi ctl= new CauTraLoi(noiDungCauTraLoi,dungsai);
            listCauTraLoi.add(ctl);

            //xulyb cautraloi2
            edt_ndCauTraLoi= v.findViewById(R.id.editText_nhapCauTraLoi2);
            noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            rg = v.findViewById(R.id.radioGroup_cauTraLoi2);
            checked = rg.getCheckedRadioButtonId();
            dungsai = ((RadioButton)v.findViewById(checked)).getText().toString();
            ctl= new CauTraLoi(noiDungCauTraLoi,dungsai);
            listCauTraLoi.add(ctl);

            //xulyb cautraloi3
            edt_ndCauTraLoi= v.findViewById(R.id.editText_nhapCauTraLoi3);
            noiDungCauTraLoi = edt_ndCauTraLoi.getText().toString();
            rg = v.findViewById(R.id.radioGroup_cauTraLoi3);
            checked = rg.getCheckedRadioButtonId();
            dungsai = ((RadioButton)v.findViewById(checked)).getText().toString();
            ctl= new CauTraLoi(noiDungCauTraLoi,dungsai);
            listCauTraLoi.add(ctl);

            listCauHoi.add(new CauHoi(noiDungCauHoi,listCauTraLoi));
            Log.d("Dap an cau", listCauTraLoi.get(0).getNoiDung());
        }

    }
    void themQuiz(){
        SharedPreferences preferences=getSharedPreferences("user_data",MODE_PRIVATE);

        String tenQuiz = ((EditText)binding.edtTenQuiz).getText().toString();
        userID = preferences.getString("id","");
        long luotlam=0;
        float rating =0;
        hinhAnh="";
        String tenDeTai = ((EditText)binding.edtTenChuDe).getText().toString();
        int thoigian = Integer.parseInt(((EditText)binding.edtThoiGian).getText().toString());
        Calendar now = GregorianCalendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        String ngayTao= format.format(now.getTime());


        FirebaseFirestore db= FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Quiz");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference photoRef= storageReference.child("image/"+userID+'/'+now.getTime());
        UploadTask uploadTask=photoRef.putFile(imgURI);
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
                    hinhAnh=uri.toString();
                    Log.d("complete uri", hinhAnh);
                Quiz newquiz= new Quiz(tenQuiz,userID,hinhAnh,tenDeTai,ngayTao,luotlam,rating,thoigian,soCauHoi,listCauHoi);
                // Toast.makeText(this, hinhAnh+"", Toast.LENGTH_SHORT).show();
                //reference.add(newquiz);
                addQuizToDataBase(newquiz);
            }
        });
//                .addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                if (task.isSuccessful()) {
//                    Uri downloadUri = task.getResult();
//                    hinhAnh=downloadUri.toString();
//                    Log.d("complete uri", hinhAnh);
//                } else {
//                    Log.e("getURL", "Fail");
//                }
//            }
//        });

    }
    void addQuizToDataBase(Quiz quiz){
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Quiz");
        reference.add(quiz).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String quizID= documentReference.getId();
                DocumentReference reference1 = db.collection("users").document(userID);
                reference1.update("listQuiz", FieldValue.arrayUnion(quizID));
                Map<String,Object> map=new HashMap<>();
                map.put("listBinhLuan",new ArrayList<BinhLuan>());
                db.collection("Comment").document(quizID).set(map);
            }
        });

    }
}