package com.nhom1_ptqlyc.quizzapp.objects;

import java.io.Serializable;

public class CauTraLoi implements Serializable {
    String noiDung;
    String dungSAi;

    public CauTraLoi() {
    }

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
