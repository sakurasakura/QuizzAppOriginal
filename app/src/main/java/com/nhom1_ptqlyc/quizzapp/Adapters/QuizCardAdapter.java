package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.Quiz;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.QuizCardHolder> {
    public class QuizCardHolder extends RecyclerView.ViewHolder {

        ImageView imageView_anhQuiz;
        TextView textView_tenQuiz;
        TextView textView_tenNgDung;
        TextView textView_chuDe;
        TextView textView_luotlam;

        public QuizCardHolder(@NonNull View itemView) {
            super(itemView);
            imageView_anhQuiz = itemView.findViewById(R.id.imgView_anhQuiz);
            textView_tenQuiz = itemView.findViewById(R.id.textView_tenQuiz);
            textView_tenNgDung = itemView.findViewById(R.id.textView_user);
            textView_chuDe = itemView.findViewById(R.id.textView_chuDe);
            textView_luotlam = itemView.findViewById(R.id.textView_luotLam);
        }
    }

    List<QuizWithID> mList;

    void setData(List<QuizWithID> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_quiz, parent, false);
        return new QuizCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizCardHolder holder, int position) {
        QuizWithID quiz = mList.get(position);
        if (quiz == null) {
            return;
        }
        Picasso.get().load(quiz.getQuiz().getHinhAnhURL()).into(holder.imageView_anhQuiz);
        holder.textView_tenQuiz.setText(quiz.getQuiz().getTen());
        holder.textView_tenNgDung.setText(quiz.getQuiz().getNguoiTao());
        holder.textView_chuDe.setText(quiz.getQuiz().getChuDe());
        holder.textView_luotlam.setText(quiz.getQuiz().getLuotLam()+"");
    }

    @Override
    public int getItemCount() {
        if (!mList.isEmpty()) {
            return mList.size() ;
        }
        return 0;
    }
    //    public QuizCardAdapter(Context mContext, ArrayList<Quiz> listQuiz) {
//        this.mContext = mContext;
//        this.listQuiz = listQuiz;
//    }
//
//    @Override
//    public int getCount() {
//        return 7;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listQuiz.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView=inflater.inflate(R.layout.card_quiz,null);
//
//        ImageView imageView_anhQuiz= convertView.findViewById(R.id.imgView_anhQuiz);
//        TextView textView_tenQuiz=convertView.findViewById(R.id.textView_tenQuiz);
//        TextView textView_tenNgDung=convertView.findViewById(R.id.textView_user);
//        TextView textView_chuDe=convertView.findViewById(R.id.textView_chuDe);
//        TextView textView_luotlam=convertView.findViewById(R.id.textView_luotLam);
//
//        Picasso.get().load(listQuiz.get(position).getQuiz().getHinhAnhURL()).into(imageView_anhQuiz);
//        textView_tenQuiz.setText(listQuiz.get(position).getQuiz().getTen());
//        textView_tenNgDung.setText(listQuiz.get(position).getQuiz().getNguoiTao());
//        textView_chuDe.setText(listQuiz.get(position).getQuiz().getChuDe());
//        textView_luotlam.setText(listQuiz.get(position).getQuiz().getLuotLam());
//        return convertView;
//    }
}
