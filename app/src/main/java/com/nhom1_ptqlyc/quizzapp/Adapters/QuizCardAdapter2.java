package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.nhom1_ptqlyc.quizzapp.Activities.UC04_XemQuiz;
import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.QuizWithID;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuizCardAdapter2 extends RecyclerView.Adapter<QuizCardAdapter2.QuizCardHolder> implements Filterable {
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
    List<QuizWithID> mListOld;
    Context mContext;

    public QuizCardAdapter2(List<QuizWithID> mList, List<QuizWithID> mListOld, Context mContext) {
        this.mList = mList;
        this.mListOld = mListOld;
        this.mContext = mContext;
    }

    void setData(List<QuizWithID> list, Context mContext) {
        this.mContext = mContext;
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
        holder.textView_luotlam.setText(quiz.getQuiz().getLuotLam() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UC04_XemQuiz.class);
                intent.putExtra("KEY_QUIZ_WITH_ID", quiz.getQuizID());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (!mList.isEmpty()) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()) {
                    mList = mListOld;
                } else {
                    ArrayList<QuizWithID> list = new ArrayList<>();
                    for (QuizWithID q : mListOld) {
                        if (q.getQuiz().getTen().toLowerCase().contains(key.toLowerCase()) || q.getQuiz().getNguoiTao().toLowerCase().contains(key.toLowerCase())) {
                            list.add(q);
                        }
                    }
                    mList = list;
                }
                FilterResults results = new FilterResults();
                results.values=mList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mList=(ArrayList<QuizWithID>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
