package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.QuizCategory_Home;

import java.util.ArrayList;

public class QuizCategoryAdapter extends RecyclerView.Adapter<QuizCategoryAdapter.QuizCategoryViewHolder> {


    Context mContext;
    ArrayList<QuizCategory_Home> listCategory;

    public QuizCategoryAdapter(Context mContext, ArrayList<QuizCategory_Home> listCategory) {
        this.mContext = mContext;
        this.listCategory = listCategory;
    }

    public QuizCategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<QuizCategory_Home> list) {
        this.listCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardquiz_row, parent, false);
        return new QuizCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizCategoryViewHolder holder, int position) {
        QuizCategory_Home category = listCategory.get(position);
        if (category == null) {
            return;
        }
        holder.textView_category.setText(category.getCategory());
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView_quiz_collection.setLayoutManager(manager);
        QuizCardAdapter quizCardAdapter = new QuizCardAdapter();
        quizCardAdapter.setData(category.getQuizWithIDS(),mContext);
        holder.recyclerView_quiz_collection.setAdapter(quizCardAdapter);


    }

    @Override
    public int getItemCount() {
        if (listCategory!=null) {
            return listCategory.size();
        }
        return 0;
    }

    public class QuizCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textView_category;
        RecyclerView recyclerView_quiz_collection;

        public QuizCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_category = itemView.findViewById(R.id.textView_category);
            recyclerView_quiz_collection = itemView.findViewById(R.id.row_quiz_collection);
        }
    }
}
