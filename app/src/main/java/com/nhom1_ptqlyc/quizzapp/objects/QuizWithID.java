package com.nhom1_ptqlyc.quizzapp.objects;

public class QuizWithID {
    String QuizID;
    Quiz quiz;

    public QuizWithID() {
    }

    public QuizWithID(String quizID, Quiz quiz) {
        QuizID = quizID;
        this.quiz = quiz;
    }

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String quizID) {
        QuizID = quizID;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
