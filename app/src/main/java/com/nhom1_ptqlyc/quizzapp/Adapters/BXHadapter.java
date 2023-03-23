package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.QuizUser;

import java.util.ArrayList;

public class BXHadapter extends BaseAdapter {
    Context mContext;
    ArrayList<QuizUser> listBXH;
    int layout;

    public BXHadapter(Context mContext, ArrayList<QuizUser> listBXH, int layout) {
        this.mContext = mContext;
        this.listBXH = listBXH;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listBXH.size();
    }

    @Override
    public Object getItem(int position) {
        return listBXH.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_bxh,null);

        TextView textView_bxh_username= convertView.findViewById(R.id.textview_bxh_username);
        TextView textView_bxh_diemCao= convertView.findViewById(R.id.textview_bxh_diemCao);

        textView_bxh_username.setText(listBXH.get(position).getIdNgDung());
        textView_bxh_diemCao.setText(listBXH.get(position).getSoDiemCaoNhat()+"");

        return convertView;
    }
}
