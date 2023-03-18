package com.nhom1_ptqlyc.quizzapp.objects;

public class CauTraLoi {
    String noiDung;
    boolean dungSai;

    public CauTraLoi(String noiDung, boolean dungSai) {
        this.noiDung = noiDung;
        this.dungSai = dungSai;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isDungSai() {
        return dungSai;
    }

    public void setDungSai(boolean dungSai) {
        this.dungSai = dungSai;
    }
}
