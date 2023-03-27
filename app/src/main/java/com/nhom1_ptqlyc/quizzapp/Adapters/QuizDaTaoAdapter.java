package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;

import java.util.ArrayList;

public class QuizDaTaoAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<QuizWithID> list;

    public QuizDaTaoAdapter(Context mContext, ArrayList<QuizWithID> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_quiz_da_tao,null);
        TextView tv_tenQuiz= convertView.findViewById(R.id.tv_tenQuiz);
        TextView tv_cdQuiz= convertView.findViewById(R.id.tv_cdQuiz);
        TextView textView_luotLam= convertView.findViewById(R.id.textView_luotLam);

        tv_tenQuiz.setText(list.get(position).getQuiz().getTen());
        tv_cdQuiz.setText(list.get(position).getQuiz().getChuDe());
        textView_luotLam.setText(list.get(position).getQuiz().getLuotLam()+"");
        return convertView;
    }
}
