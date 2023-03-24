package com.nhom1_ptqlyc.quizzapp.objects;

import java.util.ArrayList;

public class QuizCategory_Home {
    String category;
    ArrayList<QuizWithID> quizWithIDS;

    public QuizCategory_Home(String category, ArrayList<QuizWithID> quizWithIDS) {
        this.category = category;
        this.quizWithIDS = quizWithIDS;
    }

    public QuizCategory_Home() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<QuizWithID> getQuizWithIDS() {
        return quizWithIDS;
    }

    public void setQuizWithIDS(ArrayList<QuizWithID> quizWithIDS) {
        this.quizWithIDS = quizWithIDS;
    }
}
