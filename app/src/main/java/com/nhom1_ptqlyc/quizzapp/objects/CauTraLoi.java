package com.nhom1_ptqlyc.quizzapp.objects;

public class CauTraLoi {
    String noiDung;
    String dungSAi;

    public CauTraLoi(String noiDung, String dungSAi) {
        this.noiDung = noiDung;
        this.dungSAi = dungSAi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDungSAi() {
        return dungSAi;
    }

    public void setDungSAi(String dungSAi) {
        this.dungSAi = dungSAi;
    }
}
