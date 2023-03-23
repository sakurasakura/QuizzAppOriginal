package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

    public class QuizCardAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<QuizWithID> listQuiz;

    public QuizCardAdapter(Context mContext, ArrayList<Quiz> listQuiz) {
        this.mContext = mContext;
        this.listQuiz = listQuiz;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return listQuiz.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.card_quiz,null);

        ImageView imageView_anhQuiz= convertView.findViewById(R.id.imgView_anhQuiz);
        TextView textView_tenQuiz=convertView.findViewById(R.id.textView_tenQuiz);
        TextView textView_tenNgDung=convertView.findViewById(R.id.textView_user);
        TextView textView_chuDe=convertView.findViewById(R.id.textView_chuDe);
        TextView textView_luotlam=convertView.findViewById(R.id.textView_luotLam);

        Picasso.get().load(listQuiz.get(position).getQuiz().getHinhAnhURL()).into(imageView_anhQuiz);
        textView_tenQuiz.setText(listQuiz.get(position).getQuiz().getTen());
        textView_tenNgDung.setText(listQuiz.get(position).getQuiz().getNguoiTao());
        textView_chuDe.setText(listQuiz.get(position).getQuiz().getChuDe());
        textView_luotlam.setText(listQuiz.get(position).getQuiz().getLuotLam());
        return convertView;
    }
}
