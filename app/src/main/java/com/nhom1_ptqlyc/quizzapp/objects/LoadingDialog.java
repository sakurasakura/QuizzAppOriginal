package com.nhom1_ptqlyc.quizzapp.objects;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.nhom1_ptqlyc.quizzapp.R;

public class LoadingDialog {
    Activity mActivity;
    AlertDialog alertDialog;

    public LoadingDialog(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void startLoading(){
    AlertDialog .Builder builder=new AlertDialog.Builder(mActivity);
        LayoutInflater inflater =mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);
        alertDialog= builder.create();
        alertDialog.show();
    }
   public void dismissDialog(){
        alertDialog.dismiss();
    }
}
