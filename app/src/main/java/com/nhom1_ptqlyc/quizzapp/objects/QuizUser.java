package com.nhom1_ptqlyc.quizzapp.objects;

public class QuizUser {
    String idQuiz;
    String idNgDung;
    float soDiemGanNhat;
    float soDiemCaoNhat;
    int rate;

    public QuizUser() {
    }

    public QuizUser(String idQuiz, String idNgDung, float soDiemGanNhat, float soDiemCaoNhat, int rate) {
        this.idQuiz = idQuiz;
        this.idNgDung = idNgDung;
        this.soDiemGanNhat = soDiemGanNhat;
        this.soDiemCaoNhat = soDiemCaoNhat;
        this.rate = rate;
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getIdNgDung() {
        return idNgDung;
    }

    public void setIdNgDung(String idNgDung) {
        this.idNgDung = idNgDung;
    }

    public float getSoDiemGanNhat() {
        return soDiemGanNhat;
    }

    public void setSoDiemGanNhat(float soDiemGanNhat) {
        this.soDiemGanNhat = soDiemGanNhat;
    }

    public float getSoDiemCaoNhat() {
        return soDiemCaoNhat;
    }

    public void setSoDiemCaoNhat(float soDiemCaoNhat) {
        this.soDiemCaoNhat = soDiemCaoNhat;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
