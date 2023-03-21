package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.CauHoi;

import java.util.ArrayList;

public class LamQuizAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<CauHoi> listCauHoi;

    @Override
    public int getCount() {
        return listCauHoi.size();
    }

    @Override
    public Object getItem(int position) {
        return listCauHoi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_hien_thi_cau_hoi,null);

        TextView thuTuCauHoi = convertView.findViewById(R.id.textView_thuTuCauHoi);
        TextView ndCauHoi = convertView.findViewById(R.id.textView_ndCauHoi);
        RadioGroup radioGroup
        return convertView;
    }
}
